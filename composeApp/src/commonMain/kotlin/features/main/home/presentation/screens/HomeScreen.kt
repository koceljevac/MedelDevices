package features.main.home.presentation.screens

import PaymentScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.ui.components.itemDevice
import features.main.home.presentation.components.ShimmerCard
import features.main.home.presentation.components.dialogAddDevice
import features.main.home.presentation.viewmodel.HomeViewModel
import features.main.home.presentation.viewmodel.mvi.HomeState
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.avatar
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = koinInject()
        val state by viewModel.uiState.collectAsState()
        var showDialog by remember { mutableStateOf(false) }


        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { showDialog = true }) {
                    Icon(Icons.Rounded.Add, contentDescription = "Floating action button")
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                userSection(
                    modifier = Modifier.weight(1f)
                )
                cardSection(
                    state,
                    showDialog,
                    onDismissDialog = { showDialog = false },
                    modifier = Modifier.weight(3f)
                        .background(Color.Red)
                )
            }
        }
    }
}

@Composable
private fun cardSection(
    state: HomeState,
    showDialog: Boolean,
    onDismissDialog: () -> Unit,
    modifier: Modifier
) {

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            HomeState.Loading -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(5) {
                        ShimmerCard()
                    }
                }
            }

            is HomeState.DevicesLoaded -> {
                val devices = state.devices
                LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(devices) { device ->
                        itemDevice(device)
                    }
                }
            }
            HomeState.Initial -> {}
            is HomeState.Error -> {
                println("NA EKRANU ERROR")
                Text("Error")
            }
        }

        AnimatedVisibility(
            visible = showDialog,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            if (showDialog) {
                dialogAddDevice(onDismiss = onDismissDialog)
            }
        }
    }
}

@Composable
private fun userSection(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(Res.drawable.avatar),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Ime Korisnika",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Uloga Korisnika",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Rounded.Notifications, contentDescription = "Localized description")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            yourBalanceSection("323,201")
        }
    }
}

@Composable
private fun yourBalanceSection(balance: String) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val navigator = LocalNavigator.currentOrThrow

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "Your balance",
                    style = typography.bodySmall.copy(color = colorScheme.onPrimaryContainer)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "$ $balance",
                    style = typography.headlineMedium.copy(color = colorScheme.onPrimaryContainer)
                )
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(colorScheme.secondary, shape = CircleShape)
            ) {
                IconButton(onClick = { navigator.push(PaymentScreen) }, modifier = Modifier.align(Alignment.Center)) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add",
                        tint = colorScheme.onSecondary
                    )
                }
            }
        }
    }
}
