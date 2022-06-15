package com.flexcode.jetweather.presentation.compossables.bottomnavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.flexcode.jetweather.ui.theme.*

@Composable
fun BottomNavigationBar(
    navController: NavController,
    showBottomNav: Boolean = true,
    items: List<BottomNavigationItem> = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.Alert,
    ),
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        backgroundColor = DarkBackground,
        bottomBar = {
            if (showBottomNav) {
                BottomNavigation(
                    backgroundColor = DarkBackground,
                    contentColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontSize = 14.sp
                                )
                            },
                            selectedContentColor = Purple700,
                            unselectedContentColor = IconGray,
                            alwaysShowLabel = true,
                            selected = currentDestination?.route?.contains(item.destination.route) == true,
                            onClick = {
                                navController.navigate(item.destination.route) {
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }

                }
            }
        }

    ) {
        content(it)
    }
}