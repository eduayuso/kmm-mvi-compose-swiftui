package dev.eduayuso.kmcs.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class MainDispatcher {

    actual val dispatcher: CoroutineDispatcher = Dispatchers.Main
}