package features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import features.main.calendar.presentation.screens.CalendarScreen
import features.main.favorites.presentation.screens.FavoritesScreen
import features.main.home.presentation.screens.HomeTab
import features.main.profile.presentation.screens.ProfileTab

class MainScreen() : Screen {

    @Composable
    override fun Content() {
        MainScreenContent()
    }
}
@Composable
private fun MainScreenContent(){
    TabNavigator(HomeTab) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar {
                    val tabs = listOf(HomeTab, FavoritesScreen, CalendarScreen, ProfileTab())
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