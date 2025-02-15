package com.eonoohx.mytuxtla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.eonoohx.mytuxtla.ui.MyTuxtlaApp
import com.eonoohx.mytuxtla.ui.theme.MyTuxtlaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            MyTuxtlaTheme {
                MyTuxtlaApp(windowSize.widthSizeClass)
            }
        }
    }
}
