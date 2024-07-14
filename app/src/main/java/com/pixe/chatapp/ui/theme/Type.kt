package com.pixe.chatapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.pixe.chatapp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val circularStdFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Circular Std"),
        fontProvider = provider
    )
)

val carosFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Caros"),
        fontProvider = provider
    )
)


// Set of Material typography styles to start with
val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    ),

    titleLarge = TextStyle(
        fontFamily = carosFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 40.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = carosFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = carosFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),

    displayLarge = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 78.sp,
        lineHeight = 78.sp,
        letterSpacing = 0.sp
    ),

    labelLarge = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp
    )
)