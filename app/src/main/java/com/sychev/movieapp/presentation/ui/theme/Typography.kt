package com.sychev.movieapp.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.sychev.movieapp.R

private val Spartan = fontFamily(
    font(R.font.spartan_thin, FontWeight.W300),
    font(R.font.spartan_regular, FontWeight.W400),
    font(R.font.spartan_semi_bold, FontWeight.W500),
    font(R.font.spartan_bold, FontWeight.W600),
    font(R.font.spartan_extra_bold, FontWeight.W700),
)

val SpartanTypography = Typography(
    h1 = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    h4 = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W300,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Spartan,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W500,
        fontSize = 15.sp,
        color = Color.White
    ),
    caption = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Spartan,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)