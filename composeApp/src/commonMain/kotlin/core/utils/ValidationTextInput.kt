package core.utils

fun validateUsername(username: String): String? {
    return if (username.isBlank()) {
        "Username cannot be empty"
    } else {
        null
    }
}

fun validateEmail(email: String): String? {
    val emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"
    return if (email.isBlank()) {
        "Email cannot be empty"
    } else if (!email.matches(emailPattern.toRegex())) {
        "Invalid email address"
    } else {
        null
    }
}


fun validatePassword(password: String): String? {
    return if (password.isBlank()) {
        "Password cannot be empty"
    } else if (password.length < 6) {
        "Password must be at least 6 characters"
    } else {
        null
    }
}
