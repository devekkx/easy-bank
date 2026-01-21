package com.devekkx.easy_bank.ui.theme

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = White,
    tertiary = Pink40,
    secondary = Secondary,
    onSecondary = Black,

    background = Black,
    onBackground = White,

    surface = Black,
    onSurface = White,

    error = Error
)


val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = White,
    primaryContainer = PrimaryLight,
    tertiary = Pink40,
    secondary = Secondary,
    onSecondary = Black,
    secondaryContainer = SecondaryLight,

    background = White,
    onBackground = Black,

    surface = White,
    onSurface = Black,

    error = Error,
    onError = White,

    outline = Neutral200
)

val AuthLightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = White,
    primaryContainer = PrimaryLight,
    tertiary = Pink40,
    secondary = Secondary,
    onSecondary = Black,
    secondaryContainer = SecondaryLight,

    background = Primary,
    onBackground = Black,

    surface = White,
    onSurface = Black,

    error = Error,
    onError = White,

    outline = Neutral200
)

@Composable
fun colorSchemeSwitcher(
    dynamicColor: Boolean,
    lightColorScheme: ColorScheme = LightColorScheme,
    darkColorScheme: ColorScheme = DarkColorScheme
): ColorScheme {
//    val darkTheme: Boolean = isSystemInDarkTheme()
    val darkTheme: Boolean = false

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    return colorScheme
}

@Composable
fun EasyBankTheme(

    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }

    val colorScheme = colorSchemeSwitcher(dynamicColor)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


@Composable
fun AppTheme(dynamicColor: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorSchemeSwitcher(dynamicColor),
        typography = Typography,
        content = content
    )
}

@Composable
fun AuthTheme(dynamicColor: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorSchemeSwitcher(dynamicColor, AuthLightColorScheme, AuthLightColorScheme),
        typography = Typography,
        content = content,
    )
}
