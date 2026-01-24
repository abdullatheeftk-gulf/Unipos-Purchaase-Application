buildscript {
    extra.apply {
        set("compose_ui_version", "1.10.1")
        // Downgrade to stable 2.1.0 to ensure KSP finds a matching plugin
        set("kotlin_version", "2.1.0")
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.59")
        // Update this to 2.1.0
        classpath("org.jetbrains.kotlin:kotlin-serialization:2.1.0")
        classpath("com.google.gms:google-services:4.4.4")
        classpath("com.google.firebase:firebase-crashlytics-gradle:3.0.6")
    }
}

plugins {
    id("com.android.application") version "9.0.0" apply false
    id("com.android.library") version "9.0.0" apply false

    // FIX: Use stable Kotlin 2.1.0
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false

    // FIX: Use the matching KSP version for 2.1.0 (This version definitely exists)
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
}