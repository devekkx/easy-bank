package com.devekkx.easy_bank

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.devekkx.easy_bank.ui.theme.EasyBankTheme
import com.devekkx.easy_bank.ui.theme.SplashScreenViewModel

class MainActivity : ComponentActivity() {
    private val splashScreenViewModel: SplashScreenViewModel by lazy {
        ViewModelProvider(this@MainActivity)[SplashScreenViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
        setKeepOnScreenCondition { splashScreenViewModel.isSplashScreenVisible.value }
            setOnExitAnimationListener { splash ->
                val rotationAnimator = ValueAnimator.ofFloat(0f, 270f)
                rotationAnimator.duration = 500
                rotationAnimator.addUpdateListener {
                 splash.iconView.rotation = it.animatedValue as Float
                }
                rotationAnimator.doOnEnd { splash.remove() }
                rotationAnimator.start()
                }
        }

        enableEdgeToEdge()
        setContent {
            EasyBankTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EasyBankTheme {
        Greeting("Android")
    }
}