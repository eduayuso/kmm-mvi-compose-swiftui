object Dependencies {
    
    private val versions = Versions.Dependencies

    object Shared {

        val commonMain = listOf(
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${versions.serialization}",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.coroutines}",
            "io.ktor:ktor-client-core:${versions.ktor}",
            "io.ktor:ktor-client-content-negotiation:${versions.ktor}",
            "io.ktor:ktor-serialization-kotlinx-json:${versions.ktor}",
            "io.ktor:ktor-client-logging:${versions.ktor}",
            "io.insert-koin:koin-core:${versions.koin}",
            "co.touchlab:kermit:${versions.kermit}",
        )

        val commonTest = listOf(
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${versions.coroutines}",
            "io.ktor:ktor-client-mock:${versions.ktor}",
            "io.insert-koin:koin-test:${versions.koinTest}",
            "io.mockk:mockk-common:${versions.mockkCommon}",
            "app.cash.turbine:turbine:${versions.turbine}"
        )

        val commonKotlin = listOf(
            "stdlib-common"
        )

        val commonKotlinTest = listOf(
            "test-common",
            "test-annotations-common"
        )

        val androidMain = listOf(
            "io.ktor:ktor-client-okhttp:${versions.ktor}"
        )

        val androidKotlinTest = listOf(
            "test-junit"
        )

        val androidUnitTest = listOf(
            "junit:junit:4.13.2",
            "io.mockk:mockk:${versions.mockk}",
            "app.cash.turbine:turbine:${versions.turbine}"
        )

        val iosMain = listOf(
            "io.ktor:ktor-client-darwin:${versions.ktor}"
        )
    }

    val androidPlatform = listOf(
        "androidx.compose:compose-bom:${versions.composeBom}"
    )

    val android = listOf(
        "androidx.compose.ui:ui",
        "androidx.compose.ui:ui-tooling",
        "androidx.compose.ui:ui-tooling-preview",
        "androidx.compose.foundation:foundation",
        "androidx.compose.material:material",
        "androidx.compose.material3:material3:${versions.material3}",
        "androidx.activity:activity-compose:${versions.activity}",
        "androidx.navigation:navigation-compose:${versions.navigation}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.coroutines}",
        "io.insert-koin:koin-android:${versions.koinAndroid}",
        "io.insert-koin:koin-androidx-compose:${versions.koinCompose}",
        "io.coil-kt:coil-compose:${versions.coil}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${versions.lifecycle}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${versions.lifecycle}",
        "androidx.lifecycle:lifecycle-viewmodel-compose:${versions.lifecycle}",
        "androidx.lifecycle:lifecycle-livedata-ktx:${versions.lifecycle}"
    )
}