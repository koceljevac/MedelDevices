package features.main.profile.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import core.ui.data_lists.getProfileSettingsList
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.avatar
import org.jetbrains.compose.resources.painterResource

class ProfileScreen :Screen {
    @Composable
    override fun Content() {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                UserInformation()
                ProfileMenu()
            }
        }
    }
}
@Composable
 fun UserInformation(){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(Res.drawable.avatar),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Nikola Rankovic",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "=381613068323",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )
        Text(
            text = "Admin",
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray
        )
    }
}
@Composable
fun ProfileMenu() {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary
        )
        LazyColumn {
            val profileSettingsList = getProfileSettingsList()

            items(profileSettingsList) { profileSetting ->
                profileMenuItem(icon = profileSetting.imageVector, name = profileSetting.name)
            }
        }
    }

}
@Composable
fun profileMenuItem(icon: ImageVector, name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}