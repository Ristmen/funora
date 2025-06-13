package com.pb.funora.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pb.funora.core.navigation.AppNavGraph
import com.pb.funora.core.theme.FunoraTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunoraTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    AppNavGraph(navController)
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    FunoraTheme {
        val navController = rememberNavController()
        AppNavGraph(navController)
    }
}
