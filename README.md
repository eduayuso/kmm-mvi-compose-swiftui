# Kotlin Multiplatform Mobile with Jetpack Compose and SwiftUI

This is a starting point for **KMM (Kotlin Multiplatform Mobile)** projects designed with **Clean Arquitecture** and **MVI (Model View Intent)**, intended to be understable, testable and scalable. This project is preconfigured with essentials libraries and tools, as well as modules, interfaces, base classes, unit and integration tests, just ready for you to start your Android and iOS app.

* Kotlin Multiplatform
* MVI (Model View Intent)
* UI layer: Jetpack Compose (Android) and SwiftUI (iOS)

## Setup
* The app use the API https://dummyapi.io/ to fetch the data, you have to indicate the app id of your dummyapi.io account.
* For Android app add a property called "dummyapi.key" in local.properties (in project root) with yout dummyapi.io app id.
* For iOS add a custom property called: "DummyApiKey" with yout dummyapi.io app id.

## Android:
![Alt Text](demo.gif)

## iOS:
![Alt Text](demo-ios.gif)