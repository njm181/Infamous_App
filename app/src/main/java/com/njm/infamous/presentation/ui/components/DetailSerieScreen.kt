package com.njm.infamous.presentation.ui.components

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.njm.infamous.R
import com.njm.infamous.domain.entity.GenreList
import com.njm.infamous.domain.entity.Result
import com.njm.infamous.presentation.ui.theme.InfamousTheme
import com.njm.infamous.presentation.utils.Resource
import com.njm.infamous.presentation.viewModel.SerieViewModel
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun ImageDetailBackground(serie: Result) {
    Column() {
        GlideImage(
            data = "https://image.tmdb.org/t/p/original" + serie.backdrop_path,
            contentDescription = "My content description",
            modifier = Modifier
                .wrapContentHeight(),
            contentScale = ContentScale.Crop,
            fadeIn = true,
            loading = {
                Box(
                    Modifier
                        .matchParentSize()
                        .height(200.dp)) {
                    CircularProgressBar(isDisplayed = true)
                }
            },
            error = {
                Image(
                    painter = painterResource(R.drawable.cinta_cine_medium),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(120.dp)
                )
            }
        )
    }
}

@Composable
fun DescriptionDetailsSerie(serie: Result, serieGenres: List<String>) {
    var genresName: String = ""
    for (genre in serieGenres) {
        genresName += "$genre | "
    }
    Column(modifier = Modifier.padding(4.dp)) {
        Text(
            text = serie.original_name,
            fontSize = 20.sp,
            modifier = Modifier.padding(2.dp),
            color = Color.White
        )
        Text(
            text = serie.overview,
            fontSize = 15.sp,
            modifier = Modifier.padding(2.dp),
            textAlign = TextAlign.Justify,
            color = Color.White
        )
        Text(
            text = "Fecha de estreno: ${serie.first_air_date}",
            fontSize = 12.sp,
            modifier = Modifier.padding(2.dp),
            color = Color.White
        )
        Text(
            text = "Genero: $genresName",
            fontSize = 12.sp,
            modifier = Modifier.padding(2.dp),
            color = Color.White
        )

    }
}

@Composable
fun RecomendationDetailSerie(seriesList: List<Result>, navController: NavController) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(top = 4.dp, bottom = 4.dp)
    ) {
        itemsIndexed(seriesList){ index, serie ->
            CardDetailSerie(serie = serie, navController, index)
        }
    }
}

@Composable
fun ImageCardSerieDetail(serie: Result) {
    Surface(shape = RoundedCornerShape(8.dp)) {
        GlideImage(
            data = "https://image.tmdb.org/t/p/original"+serie.backdrop_path,
            contentDescription = "My content description",
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth,
            fadeIn = true,
            loading = {
                Box(Modifier.matchParentSize()) {
                    CircularProgressBar(isDisplayed = true)
                }
            },
            error = {
                Image(
                    painter = painterResource(R.drawable.cinta_cine_medium),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(120.dp)
                )
            }
        )
    }
}

@Composable
fun CardDetailSerie(serie: Result, navController: NavController, index: Any) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .height(160.dp)
            .padding(4.dp).clickable {
                val bundle = Bundle()
                bundle.putParcelable("serie", serie)
                navController.navigate(R.id.detailSerieFragment, bundle)
            }
    ) {
        Column(
            modifier = Modifier
                .width(120.dp)
                .fillMaxHeight()
        ) {
            ImageCardSerieDetail(serie = serie)
            Spacer(modifier = Modifier.padding(top = 4.dp))
            TextSerieDescription(serie.original_name)
            RatingSerie(voteAverage = serie.vote_average)
        }
    }
}

@Composable
fun GetRecommendationsSeries(serie: Result, navController: NavController, genreNameList: List<String>, serieViewModel: SerieViewModel){
    serieViewModel.setIdSerie(serie.id)
    val recommendationsSerieList: Resource<List<Result>> by serieViewModel.getRecommendationsSeries.observeAsState(
        initial = Resource.Loading()
    )
    when (recommendationsSerieList) {
        is Resource.Loading -> {
            CircularProgressBar(true)
        }
        is Resource.Success -> {
            CircularProgressBar(false)
            DetailSerieScreenContent(
                navController = navController,
                serieGenres = genreNameList,
                serie = serie,
                recommendations = (recommendationsSerieList as Resource.Success<List<Result>>).data
            )
        }
        is Resource.Failure -> {
            CircularProgressBar(false)
        }
    }
}

@Composable
fun GetGenresSerie(serieViewModel: SerieViewModel, serie: Result, navController: NavController){
    val genreListResult: Resource<GenreList> by serieViewModel.getGenreList.observeAsState(
        initial = Resource.Loading()
    )

    var genreNameList = ArrayList<String>()
    when (genreListResult) {
        is Resource.Loading -> {
            CircularProgressBar(true)
        }
        is Resource.Success -> {
            CircularProgressBar(false)
            for (genre in (genreListResult as Resource.Success<GenreList>).data.genres) {
                if (serie.genre_ids.contains(genre.id)) {
                    genreNameList.add(genre.name)
                }
            }
            GetRecommendationsSeries(serie = serie, navController = navController, genreNameList = genreNameList, serieViewModel = serieViewModel)
        }
        is Resource.Failure -> {
            CircularProgressBar(false)
        }
    }
}

@Composable
fun DetailSerieMain(
    serie: Result,
    navController: NavController,
    serieViewModel: SerieViewModel
) {
    GetGenresSerie(serieViewModel = serieViewModel, serie = serie, navController)
}


@Composable
fun DetailSerieScreenContent(
    serie: Result,
    serieGenres: List<String>,
    navController: NavController,
    recommendations: List<Result>
) {
    InfamousTheme() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column() {
                Card(shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                    modifier = Modifier
                        .weight(1.0f)
                        .fillMaxWidth()
                ) {
                    ImageDetailBackground(serie)
                }

                Column(
                    modifier = Modifier
                        .weight(1.0f)
                        .fillMaxWidth()
                        .padding(start = 2.dp, end = 2.dp)
                        .background(color = MaterialTheme.colors.background)
                        .verticalScroll(state = rememberScrollState())
                ) {
                    DescriptionDetailsSerie(serie = serie, serieGenres = serieGenres)
                    Divider(modifier = Modifier.padding(top = 4.dp))
                    RecomendationDetailSerie(recommendations, navController)//no pasar lista por parametro,
                }
            }
        }
    }
}


@Composable
fun DetailSerieComponent(content: @Composable () -> Unit) {
    InfamousTheme() {
        Surface() {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailSeriePreview() {
    /*val context = LocalContext.current
    val navController = NavController(context)
    DetailSerieComponent{
        DetailSerieScreenContent(Recipe(1, R.drawable.butacas_medium, "Titulo"), navController)
    }*/
}