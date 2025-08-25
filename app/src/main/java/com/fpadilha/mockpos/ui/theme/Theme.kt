package com.fpadilha.mockpos.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.fpadilha.mockpos.BuildConfig

private fun getColorScheme(darkTheme: Boolean, flavor: String) = when (flavor) {
    "red" -> if (darkTheme) {
        darkColorScheme(
            primary = Red80,
            secondary = RedGrey80,
            tertiary = RedAccent80,
            surface = RedGrey80
        )
    } else {
        lightColorScheme(
            primary = Red40,
            secondary = RedGrey40,
            tertiary = RedAccent40,
            surface = RedGrey80
        )
    }
    "purple" -> if (darkTheme) {
        darkColorScheme(
            primary = Purple80,
            secondary = PurpleGrey80,
            tertiary = PurpleAccent80,
            surface = PurpleGrey80
        )
    } else {
        lightColorScheme(
            primary = Purple40,
            secondary = PurpleGrey40,
            tertiary = PurpleAccent40,
            surface = PurpleGrey80
        )
    }
    "orange" -> if (darkTheme) {
        darkColorScheme(
            primary = Orange80,
            secondary = OrangeGrey80,
            tertiary = OrangeAccent80,
            surface = OrangeGrey80
        )
    } else {
        lightColorScheme(
            primary = Orange40,
            secondary = OrangeGrey40,
            tertiary = OrangeAccent40,
            surface = OrangeGrey80
        )
    }
    else -> if (darkTheme) {
        darkColorScheme(
            primary = Default80,
            secondary = DefaultGrey80,
            tertiary = DefaultAccent80,
            surface = DefaultGrey80
        )
    } else {
        lightColorScheme(
            primary = Default40,
            secondary = DefaultGrey40,
            tertiary = DefaultAccent40,
            surface = DefaultGrey80
        )
    }
}

@Composable
fun MockPosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> {
            getColorScheme(darkTheme, BuildConfig.THEME_COLOR)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}