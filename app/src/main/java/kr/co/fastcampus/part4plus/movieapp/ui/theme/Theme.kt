package kr.co.fastcampus.part4plus.movieapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import kr.co.fastcampus.part4plus.movieapp.ui.theme.color.ColorSet
import kr.co.fastcampus.part4plus.movieapp.ui.theme.color.MyColors
import kr.co.fastcampus.part4plus.movieapp.ui.theme.color.Pink40
import kr.co.fastcampus.part4plus.movieapp.ui.theme.color.Pink80
import kr.co.fastcampus.part4plus.movieapp.ui.theme.color.Purple40
import kr.co.fastcampus.part4plus.movieapp.ui.theme.color.Purple80
import kr.co.fastcampus.part4plus.movieapp.ui.theme.color.PurpleGrey40
import kr.co.fastcampus.part4plus.movieapp.ui.theme.color.PurpleGrey80

private val LocalColors = staticCompositionLocalOf { ColorSet.Red.LightColors }
@Composable
fun MovieAppTheme(
    myColors : ColorSet,
    darkTheme : Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colors = if (darkTheme) {
        myColors.DarkColors
    } else {
        myColors.LightColors
    }

    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors = colors.material,
            typography = Typography,
            shapes = shapes,
            content = content
        )
    }
}
/*
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    */
/* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    *//*

)

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    */
/**
     * typography -> 서체
     * shape -> rounded corner shape 등 네모난 이미지를 동그랗게 만들때.. 형태 모양등
     *//*


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}*/
