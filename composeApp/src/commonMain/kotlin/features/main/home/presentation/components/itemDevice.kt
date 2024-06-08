package features.main.home.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.main.home.domain.entites.Device
import org.jetbrains.compose.ui.tooling.preview.Preview

val device = Device(id = "31231", name = "Aparat za pritisak","Available","Mali aparati", isLocked = false, inventoryNumber = "3123cl12", image = "")

@Preview
@Composable
fun itemDevice(device: Device){
    Card(modifier = Modifier.fillMaxWidth().height(200.dp)){

    }
}
