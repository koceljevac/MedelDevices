package core.ui.data_lists

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class ProfileSettings(val imageVector: ImageVector, val name:String)
fun getProfileSettingsList(): List<ProfileSettings> {
    return listOf(
        ProfileSettings(Icons.Default.AccountBox, "Account Details"),
        ProfileSettings(Icons.Default.Settings, "Transaction History"),
        ProfileSettings(Icons.Default.Lock, "Change Password"),
        ProfileSettings(Icons.Default.FavoriteBorder, "Documents and Statements")
    )
}
