package core.utils

fun validateDeviceName(name: String): String? {
    return if (name.isBlank()) {
        "Name cannot be empty."
    } else {
        null
    }
}

fun validateDeviceCategory(category: String): String? {
    return if (category.isBlank()) {
        "Category cannot be empty."
    } else {
        null
    }
}

fun validateDeviceDescription(description: String): String? {
    return if (description.isBlank()) {
        "Description cannot be empty."
    }
    else if (description.length > 128) {
        "Description too long."
    }
    else {
        null
    }
}

fun validateDeviceInventoryNumber(inventoryNumber: String): String? {
    return if (inventoryNumber.isBlank()) {
        "Inventory number cannot be empty."
    } else {
        null
    }
}