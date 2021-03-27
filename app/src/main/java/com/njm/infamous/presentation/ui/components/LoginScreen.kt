package com.njm.infamous.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.njm.infamous.R
import com.njm.infamous.presentation.ui.theme.InfamousTheme


@Composable
fun ImageLoginBackground(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.butacas_edit),
            contentDescription = "Background",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TitleLogin(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = "Welcome to Infamous",
            modifier = Modifier.padding(top = 16.dp),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            style = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_light)))
            )
    }
}


@Composable
fun ButtonLogin(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {
                navController.navigate(R.id.action_loginFragment_to_homeFragment)
            },
            border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.primary)),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.primary)),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            //shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevation(8.dp)
            ) {
             Text(text = "Ingresar", color = Color.White, fontSize = 15.sp)
        }
    }
}

@Composable
fun LoginScreenContent(navController: NavController) {
    InfamousTheme() {
        Surface {
            ImageLoginBackground()
            TitleLogin()
            ButtonLogin(navController)
        }
    }
}


@Composable
fun LoginComponent(content: @Composable () -> Unit) {
    InfamousTheme() {
        Surface() {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val context = LocalContext.current
    val navController = NavController(context)
    LoginComponent{
        LoginScreenContent(navController = navController)
    }
}
