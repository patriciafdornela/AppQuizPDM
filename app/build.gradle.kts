plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "2.1.0"
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp") version "2.0.20-1.0.24"
}

android {
    namespace = "np.com.bimalkafle.quizonline"
    compileSdk = 35

    defaultConfig {
        applicationId = "np.com.bimalkafle.quizonline"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")

    // AndroidX
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.cardview:cardview:1.0.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.compose.material3:material3-android:1.3.2")
    implementation("androidx.navigation:navigation-runtime-android:2.9.3")
//    implementation("androidx.navigation:navigation-compose-jvmstubs:2.9.3")
    implementation("androidx.navigation:navigation-compose:2.8.0")//coloquei agota
    annotationProcessor("androidx.room:room-compiler:2.7.2")
    implementation("androidx.room:room-compiler:2.7.2") {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation("androidx.room:room-runtime-android:2.7.2")
//    ksp("com.github.bumptech.glide:compiler:4.16.0")
    implementation("androidx.room:room-runtime:2.7.2")
    ksp("androidx.room:room-compiler:2.7.2") {
        exclude(group = "com.intellij", module = "annotations")
    }

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(platform("androidx.compose:compose-bom:2024.06.00"))


//    // Compose BOM agora
//    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
//
//// Compose
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.foundation:foundation")
//    implementation("androidx.compose.runtime:runtime")
//    implementation("androidx.compose.runtime:runtime-livedata")
//    implementation("androidx.compose.material3:material3")
//
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
}