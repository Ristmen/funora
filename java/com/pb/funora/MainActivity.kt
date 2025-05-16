package com.pb.funora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pb.funora.navigation.FunoraNavHost
import com.pb.funora.ui.theme.FunoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunoraTheme {
                FunoraNavHost()
            }
        }
    }
}
