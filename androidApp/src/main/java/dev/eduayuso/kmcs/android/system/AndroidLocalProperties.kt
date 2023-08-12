package dev.eduayuso.kmcs.android.system

import dev.eduayuso.kmcs.android.BuildConfig
import dev.eduayuso.kmcs.data.LocalProperties

class AndroidLocalProperties: LocalProperties {

    override val dummyApiKey: String
        get() = BuildConfig.DUMMY_API_KEY
}