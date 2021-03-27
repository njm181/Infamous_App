package com.njm.infamous.presentation.ui.components

import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njm.infamous.R
import com.njm.infamous.presentation.ui.theme.*
import androidx.navigation.NavController
import com.njm.infamous.domain.entity.Result
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun ImageCardSerie(serie: Result, index: Int) {
    Surface(shape = RoundedCornerShape(8.dp)) {
        GlideImage(
            data = "https://image.tmdb.org/t/p/original"+serie.backdrop_path,
            contentDescription = "My content description",
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
                        .height(100.dp)
                )
            }
        )
        OrderIdSerieHome(id = index+2)
    }
}


@Composable
fun TextSerieDescription(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            textAlign = TextAlign.Left,
            fontSize = 14.sp,
            style = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_light)))
        )

    }
}

@Composable
fun RatingSerie(voteAverage: Double) {
    var rating = voteAverage/2
    rating = String.format("%.1f", rating).toDouble()
    Row(modifier = Modifier.padding(start = 4.dp)) {
        Text(
            text = rating.toString(),
            color = Color.White,
            textAlign = TextAlign.Left,
            fontSize = 12.sp,
            style = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_light)))
        )
        Image(
            painter = painterResource(R.drawable.ic_start),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(start = 2.dp).width(10.dp).height(10.dp).align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun CardSerie(serie: Result, navController: NavController, index: Int){
        Card(elevation = 8.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier
                .height(180.dp)
                .padding(4.dp)
                .clickable {
                    val bundle = Bundle()
                    bundle.putParcelable("serie", serie)
                    navController.navigate(R.id.action_homeFragment_to_detailSerieFragment, bundle)
                }
        ){
            Column(modifier = Modifier
                .fillMaxWidth()) {
                ImageCardSerie(serie, index)
                Spacer(modifier = Modifier.padding(top = 4.dp))
                TextSerieDescription(serie.original_name)
                RatingSerie(voteAverage = serie.vote_average)
            }/*
    AndroidView(
        factory = { context: Context ->
            val view = LayoutInflater.from(context).inflate(R.layout.card_serie, null, false)
            // do whatever you want...
            val serieOrderId = view.findViewById<MaterialButton>(R.id.order_serie_id)
            val serieImage = view.findViewById<ImageView>(R.id.serie_img)
            val serieTitle = view.findViewById<TextView>(R.id.serie_title)
            val serieRating = view.findViewById<RatingBar>(R.id.serie_rating)
            serieOrderId.text = serie.id.toString()
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/original"+serie.backdrop_path)
                .centerCrop()
                .into(serieImage);
            serieTitle.text = serie.original_name
            serieRating.numStars = serie.vote_average.toInt()/2
            view // return the viw
        },
        update = { view ->
            // Update view
        }
    )*/
    }
}





@ExperimentalFoundationApi
@Composable
fun CardScreenContent(serieList: List<Result>, navController: NavController) {//seriesList: List<Result>
    val listState = rememberLazyListState()
    Surface {
        LazyVerticalGrid(state = listState, cells = GridCells.Fixed(2), content = {
            itemsIndexed(serieList){ index, serie ->
                CardSerie(serie = serie, navController, index)
            }
        })
    }
}


@Composable
fun CardComponent(content: @Composable () -> Unit) {
    InfamousTheme() {
        Surface() {
            content()
        }
    }
}


/*@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun CardtPreview(){
   *//* val context = LocalContext.current
    val navController = NavController(context)
    CardComponent{
        CardScreenContent(recipeList, navController)
    }*//*
}*/
