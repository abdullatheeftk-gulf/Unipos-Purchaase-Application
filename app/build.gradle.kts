
// plugins {
//     id("com.android.application")
//     id("kotlinx-serialization")
//     id("com.google.devtools.ksp")
//     id("org.jetbrains.kotlin.plugin.compose") version "2.1.0"
//     // id("org.jetbrains.kotlin.plugin.compose") version "2.3.0"
//     id("dagger.hilt.android.plugin")
//     id("com.google.gms.google-services")
//     id("com.google.firebase.crashlytics")
// }

// // Retrieve variables defined in Root build.gradle.kts
// val compose_ui_version: String by rootProject.extra

// android {
//     namespace = "com.gulfappdeveloper.project2"
//     compileSdk = 36

//     defaultConfig {
//         applicationId = "com.gulfappdeveloper.project2"
//         minSdk = 24
//         targetSdk = 36
//         versionCode = 1
//         versionName = "1.0"

//         testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//         vectorDrawables {
//             useSupportLibrary = true
//         }
//     }

//     buildTypes {
//         getByName("release") {
//             isMinifyEnabled = true
//             isShrinkResources = true
//             proguardFiles(
//                     getDefaultProguardFile("proguard-android-optimize.txt"),
//                     "proguard-rules.pro"
//             )
//         }
//     }
//     compileOptions {
//         sourceCompatibility = JavaVersion.VERSION_17
//         targetCompatibility = JavaVersion.VERSION_17
//     }
//     // kotlinOptions removed as per AGP 9.0 requirements

//     buildFeatures {
//         compose = true
//         buildConfig = true
//     }

//     packaging {
//         resources {
//             excludes.add("/META-INF/{AL2.0,LGPL2.1}")
//         }
//     }
// }

// dependencies {
//     implementation("androidx.core:core-ktx:1.17.0")
//     implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
//     implementation("androidx.activity:activity-compose:1.12.2")
//     implementation("androidx.compose.ui:ui:$compose_ui_version")
//     implementation("androidx.compose.ui:ui-tooling-preview:$compose_ui_version")
//     implementation("androidx.compose.material:material:$compose_ui_version")
//     implementation("androidx.compose.material:material-icons-extended:1.7.8")

//     testImplementation("junit:junit:4.13.2")
//     androidTestImplementation("androidx.test.ext:junit:1.3.0")
//     androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
//     androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_ui_version")
//     debugImplementation("androidx.compose.ui:ui-tooling:$compose_ui_version")
//     debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_ui_version")

//     // KotlinX Serialization
//     implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

//     // Ktor client
//     val ktorVersion = "2.1.2"
//     implementation("io.ktor:ktor-client-android:$ktorVersion")
//     implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
//     implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
//     implementation("io.ktor:ktor-client-logging:$ktorVersion")

//     // Dagger - Hilt
//     implementation("com.google.dagger:hilt-android:2.58")
//     ksp("com.google.dagger:hilt-android-compiler:2.58")
//     ksp("androidx.hilt:hilt-compiler:1.3.0")
//     implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

//     // Compose navigation
//     val navVersion = "2.9.5"
//     implementation("androidx.navigation:navigation-compose:$navVersion")

//     // Coil image loading
//     implementation("io.coil-kt:coil-compose:2.7.0")

//     // DataStore Preferences
//     implementation("androidx.datastore:datastore-preferences:1.2.0")

//     // Firebase
//     implementation(platform("com.google.firebase:firebase-bom:34.8.0"))
//     implementation("com.google.firebase:firebase-analytics")
//     implementation("com.google.firebase:firebase-crashlytics")
//     implementation("com.google.firebase:firebase-firestore")
// }

plugins {
    id("com.android.application")
   // id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0"
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.dagger.hilt.android")
}

val compose_ui_version: String by rootProject.extra

android {
    namespace = "com.gulfappdeveloper.project2"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.gulfappdeveloper.project2"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
//    kotlinOptions {
//        jvmTarget = "17"
//    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
    // composeOptions {
    //     // You referenced 'compose_compliler_version'. Ensure this is defined.
    //     // Otherwise, replace with your compiler version, e.g., "1.5.14"
    //     kotlinCompilerExtensionVersion = "1.5.14" 
    // }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Define versions here or use Version Catalog (libs.versions.toml)
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
    implementation("androidx.activity:activity-compose:1.12.2")
    implementation("androidx.compose.ui:ui:$compose_ui_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_ui_version")
    implementation("androidx.compose.material:material:$compose_ui_version")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
    implementation("androidx.compose.material3:material3:1.4.0")
    implementation("androidx.compose.ui:ui-viewbinding:1.10.1")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_ui_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_ui_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_ui_version")

    // KotlinX Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    
    // Ktor client
    val ktorVersion = "2.1.2"
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.58")
    ksp("com.google.dagger:hilt-android-compiler:2.58")
    ksp("androidx.hilt:hilt-compiler:1.3.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

    // Compose navigation
    val navVersion = "2.9.5"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // QR Code and Barcode
    implementation("com.journeyapps:zxing-android-embedded:4.3.0") {
        isTransitive = false
    }
    implementation("com.google.zxing:core:3.5.3")

    // To get public ip address
    implementation("com.github.chintan369:Ipify-Android:1.0.1")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.2.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:34.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-firestore")

    // Room
    val room_version = "2.8.4"
    implementation("androidx.room:room-ktx:${room_version}")
    annotationProcessor("androidx.room:room-compiler:${room_version}")

    ksp("androidx.room:room-compiler:${room_version}")

    // Calendar
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:1.1.1")
    implementation("com.maxkeppeler.sheets-compose-dialogs:calendar:1.1.1")
    implementation("com.maxkeppeler.sheets-compose-dialogs:clock:1.1.1")
}


