package features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import features.main.calendar.presentation.screens.CalendarScreen
import features.main.favorites.presentation.screens.FavoritesScreen
import features.main.home.presentation.HomeScreen
import features.main.profile.presentation.screens.ProfileScreen

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val tabs = listOf(HomeScreen, FavoritesScreen, CalendarScreen, ProfileScreen)
        TabNavigator(HomeScreen) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomAppBar {
                        tabs.forEach { tab ->
                            TabNavigationItem(tab = tab)
                        }
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let { Icon(painter = it, contentDescription = tab.options.title) }
        },
        label = {
            Text(tab.options.title)
        }
    )
}
