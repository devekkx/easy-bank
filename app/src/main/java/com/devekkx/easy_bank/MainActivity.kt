package com.devekkx.easy_bank

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.devekkx.easy_bank.ui.theme.SplashScreenViewModel

class MainActivity : ComponentActivity() {
    private val splashScreenViewModel: SplashScreenViewModel by lazy {
        ViewModelProvider(this@MainActivity)[SplashScreenViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition { splashScreenViewModel.isSplashScreenVisible.value }

            setOnExitAnimationListener { splashProvider ->
                val iconView = splashProvider.iconView

                // Scale from 100% down to 0%
                val scaleX = ObjectAnimator.ofFloat(iconView, View.SCALE_X, 1f, 0f)
                val scaleY = ObjectAnimator.ofFloat(iconView, View.SCALE_Y, 1f, 0f)


                AnimatorSet().apply {
                    // LinearInterpolator makes the shrink start slow and speed up
                    interpolator = LinearInterpolator()
                    duration = 500L
                    playTogether(scaleX, scaleY)
                    doOnEnd {
                        splashProvider.remove()
                    }
                    start()
                }
            }
        }

        enableEdgeToEdge()
        setContent {
        }
        setTheme(R.style.Theme_EasyBank)
    }
}
