package org.example.project

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import features.main.profile.presentation.screens.profileMenuItem
import features.main.profile.presentation.screens.userInformation
import org.jetbrains.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview()
@Composable
fun AppAndroidPreview() {

}