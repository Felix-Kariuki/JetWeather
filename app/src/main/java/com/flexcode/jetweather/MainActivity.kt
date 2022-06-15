package com.flexcode.jetweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.flexcode.jetweather.presentation.compossables.bottomnavigation.BottomNavigationBar
import com.flexcode.jetweather.presentation.compossables.screens.NavGraphs
import com.flexcode.jetweather.ui.theme.JetWeatherTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetWeatherTheme {
                val navController = rememberNavController()
                val navHostEngine = rememberNavHostEngine()

                BottomNavigationBar(navController = navController) {
                    Box(modifier = Modifier.padding(it)) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController,
                            engine = navHostEngine
                        )

                    }
                }
            }
        }
    }
}


