package core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.main.home.domain.entites.Device

@Composable
fun itemDevice(device: Device){
    Card(modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp), shape = RoundedCornerShape(25.dp),
    ){

    }
}
