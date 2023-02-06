package dev.eduayuso.kmcs.util

private const val EMAIL_REGEX = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)"
private const val PASSWORD_MIN_LENGTH = 6

fun String.isValidEmail() = EMAIL_REGEX.toRegex().matches(this)

fun String.isValidPassword() = this.length >= PASSWORD_MIN_LENGTH