plugins {
    // Android Gradle Plugin (AGP)
    id("com.android.application") version "8.7.3" apply false
    id("com.android.library") version "8.7.3" apply false

    // Kotlin plugin (must match your Kotlin version, not AGP)
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false

    // KSP (symbol processing, must match Kotlin version)
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false

    // Hilt plugin
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}
