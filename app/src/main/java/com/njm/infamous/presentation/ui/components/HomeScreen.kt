package com.njm.infamous.presentation.ui.components

import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.njm.infamous.R
import com.njm.infamous.domain.entity.Result
import com.njm.infamous.presentation.ui.theme.InfamousTheme
import com.njm.infamous.presentation.utils.Resource
import com.njm.infamous.presentation.viewModel.SerieViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun TitleHome() {
    Text(
        text = "Most Popular Series",
        modifier = Modifier.padding(start = 8.dp, top = 16.dp),
        color = Color.White,
        fontSize = 25.sp,
        style = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_light)))
    )
}

@Composable
fun OrderIdSerieHome(id: Int) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(colorResource(R.color.secondary_dark))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = id.toString(),
                color = Color.White,
                fontSize = 12.sp
            )
        }

    }
}

@Composable
fun ImageBackgroundHome(serie: Result, navController: NavController, origin: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .wrapContentHeight()
    ) {

        GlideImage(
            data = "https://image.tmdb.org/t/p/original" + serie.backdrop_path,
            contentDescription = "My content description",
            modifier = Modifier
                .fillMaxHeight()
                .clickable {
                    when(origin){
                        "home" -> {
                            val bundle = Bundle()
                            bundle.putParcelable("serie", serie)
                            navController.navigate(R.id.action_homeFragment_to_detailSerieFragment, bundle)
                        }
                        "detail" ->{
                            val bundle = Bundle()
                            bundle.putParcelable("serie", serie)
                            navController.navigate(R.id.detailSerieFragment, bundle)
                        }
                    }
                },
            contentScale = ContentScale.FillWidth,
            fadeIn = true,
            loading = {
                Box(
                    Modifier
                        .matchParentSize()
                        .height(120.dp)
                ) {
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
        OrderIdSerieHome(id = 1)
    }
}

@Composable
fun PortadaHome(firstSerie: Result, navController: NavController) {
    Surface(modifier = Modifier.height(200.dp)) {
        ImageBackgroundHome(firstSerie, navController = navController, origin = "home")
        Column(verticalArrangement = Arrangement.Bottom) {
            TextSerieDescription(firstSerie.original_name)
            RatingSerie(voteAverage = firstSerie.vote_average)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun BodyHome(navController: NavController, seriesList: List<Result>) {
    Surface() {
        CardComponent {
            CardScreenContent(serieList = seriesList, navController = navController)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun HomeMain(
    navController: NavController,
    serieViewModel: SerieViewModel
) {
    val seriesResult: Resource<List<Result>> by serieViewModel.getPopularSeries.observeAsState(
        initial = Resource.Loading()
    )
    when (seriesResult) {
        is Resource.Loading -> {
            CircularProgressBar(true)
        }
        is Resource.Success -> {
            CircularProgressBar(false)
            HomeScreenContent(
                navController = navController,
                data = (seriesResult as Resource.Success<List<Result>>).data
            )
        }
        is Resource.Failure -> {
            CircularProgressBar(false)
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun HomeScreenContent(
    navController: NavController,
    data: List<Result>,
) {
    InfamousTheme() {
        Column(modifier = Modifier.padding(8.dp)) {
            TitleHome()
            Spacer(modifier = Modifier.padding(top = 8.dp))
            PortadaHome(firstSerie = data[0], navController = navController)
            Spacer(modifier = Modifier.padding(top = 16.dp))
            BodyHome(navController, data.drop(1))
        }
    }
}

@Composable
fun HomeComponent(content: @Composable () -> Unit) {
    InfamousTheme() {
        Surface() {
            content()
        }
    }

}


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    /*val context = LocalContext.current
    val navController = NavController(context)
    HomeComponent {
        HomeScreenContent(

        (1, R.drawable.cinta_cine_medium,"Arrozmate"), navController)
    }*/
}