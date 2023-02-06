package dev.eduayuso.kmcs.executor

import kotlinx.coroutines.CoroutineDispatcher

expect class MainDispatcher() {

    val dispatcher: CoroutineDispatcher
}