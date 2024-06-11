package features.main.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import core.utils.validateDeviceCategory
import core.utils.validateDeviceDescription
import core.utils.validateDeviceInventoryNumber
import core.utils.validateDeviceName
import features.main.home.domain.entites.Device
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.qr_code
import org.jetbrains.compose.resources.painterResource


@Composable
fun dialogAddDevice(onDismiss: () -> Unit, onSubmit: (Device) -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = true),
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.surface)
            ){
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(16.dp)
                ) {
                    Text(text = "Add Device", style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(16.dp))

                    var name by remember { mutableStateOf("") }
                    var status by remember { mutableStateOf("Available") }
                    var category by remember { mutableStateOf("") }
                    var description by remember { mutableStateOf("") }
                    var price by remember { mutableStateOf(0.0f) }
                    var inventoryNumber by remember { mutableStateOf("") }
                    var image by remember { mutableStateOf("") }

                    var nameError by remember { mutableStateOf<String?>(null) }
                    var categoryError by remember { mutableStateOf<String?>(null) }
                    var descriptionError by remember { mutableStateOf<String?>(null) }
                    var inventoryNumberError by remember { mutableStateOf<String?>(null) }

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            nameError = validateDeviceName(it)
                        },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = nameError != null
                    )
                    if (nameError != null) {
                        Text(text = nameError!!, color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    StatusDropdown(
                        selectedStatus = status,
                        onStatusSelected = { status = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = category,
                        onValueChange = {
                            category = it
                            categoryError = validateDeviceCategory(it)
                        },
                        label = { Text("Category") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = categoryError != null
                    )
                    if (categoryError != null) {
                        Text(text = categoryError!!, color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = description,
                        onValueChange = {
                            description = it
                            descriptionError = validateDeviceDescription(it)
                        },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = descriptionError != null
                    )
                    if (descriptionError != null) {
                        Text(text = descriptionError!!, color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    PriceInputField(
                        initialPrice = price,
                        onPriceChange = { newPrice ->
                            price = newPrice
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = inventoryNumber,
                        onValueChange = {
                            inventoryNumber = it
                            inventoryNumberError = validateDeviceInventoryNumber(it)
                        },
                        label = { Text("Inventory number") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = inventoryNumberError != null
                    )
                    if (inventoryNumberError != null) {
                        Text(text = inventoryNumberError!!, color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = image,
                        onValueChange = { image = it },
                        label = { Text("Image") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.qr_code), // Replace with your actual drawable resource name
                                modifier = Modifier.size(24.dp),
                                contentDescription = "QR code",
                                tint = Color.DarkGray
                            )
                        }
                        Row {
                            TextButton(onClick = onDismiss) {
                                Text("Cancel")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            TextButton(onClick = {
                                nameError = validateDeviceName(name)
                                categoryError = validateDeviceCategory(category)
                                descriptionError = validateDeviceDescription(description)
                                inventoryNumberError =
                                    validateDeviceInventoryNumber(inventoryNumber)

                                if (nameError == null || categoryError == null || descriptionError == null || inventoryNumberError == null) {
                                    val device = Device(
                                        id = "",
                                        name = name,
                                        status = status,
                                        description = description,
                                        price = price,
                                        inventoryNumber = inventoryNumber,
                                        category = category,
                                        isLocked = false,
                                        image = image
                                    )
                                    onSubmit(device)
                                }
                            }) {
                                Text("Save")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PriceInputField(
    initialPrice: Float,
    onPriceChange: (Float) -> Unit
) {
    var priceText by remember { mutableStateOf(initialPrice.toString()) }
    var priceError by remember { mutableStateOf<String?>(null) }

    OutlinedTextField(
        value = priceText,
        onValueChange = { newValue ->
            priceText = newValue
            val newPrice = newValue.toFloatOrNull()
            if (newPrice != null) {
                priceError = null
                onPriceChange(newPrice)
            } else {
                priceError = "Price must be a number."
            }
        },
        label = { Text("Price") },
        modifier = Modifier.fillMaxWidth(),
        isError = priceError != null
    )
    if (priceError != null) {
        Text(text = priceError!!, color = MaterialTheme.colorScheme.error)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusDropdown(
    selectedStatus: String,
    onStatusSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val statusOptions = listOf("Available", "Unavailable")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedStatus,
            onValueChange = {},
            readOnly = true,
            label = { Text("Status") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown icon",
                )
            },
            modifier = Modifier.fillMaxWidth().menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            statusOptions.forEach { status ->
                DropdownMenuItem(
                    text = { Text(status) },
                    onClick = {
                        onStatusSelected(status)
                        expanded = false
                    }
                )
            }
        }
    }
}