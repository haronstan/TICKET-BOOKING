@file:Suppress("UNUSED_EXPRESSION")

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.googleGmsGoogleServices)
}
val darajaConsumerKey = System.getenv("DARAJA_CONSUMER_KEY") ?: "y1gBdlmMm1eMnY0xWcG0tvOAA1ADq0xAd4u9bx2mzP0GsYzg"
val darajaConsumerSecret = System.getenv("DARAJA_CONSUMER_SECRET") ?: "7BReXdLAvzXlin221Ug9zZtSmyLpMXrtrNJtrorrPBy8SU5FbyGwWSmz4vNYCBaA"


android {
    namespace = "com.example.ticketbooking"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ticketbooking"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        // Applying consumer keys to all build types using forEach
        forEach { buildType ->
            buildType.buildConfigField("String", "CONSUMER_KEY", "\"$darajaConsumerKey\"")
            buildType.buildConfigField("String", "CONSUMER_SECRET", "\"$darajaConsumerSecret\"")
        }
    }

    buildFeatures {
        // Enable custom BuildConfig fields
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "CONSUMER_KEY", "\"y1gBdlmMm1eMnY0xWcG0tvOAA1ADq0xAd4u9bx2mzP0GsYzg\"")
        buildConfigField ("String", "CONSUMER_SECRET", "\"7BReXdLAvzXlin221Ug9zZtSmyLpMXrtrNJtrorrPBy8SU5FbyGwWSmz4vNYCBaA\"")
        multiDexEnabled = true
    }


    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.squareup.picasso:picasso:2.71828")

    //mpesa intergration
   //implementation ("com.jakewharton:butterknife:10.1.0")
   //annotationProcessor ("com.jakewharton:butterknife-compiler:10.1.0")
    implementation ("com.jakewharton.timber:timber:4.7.1")
    implementation("cn.pedant.sweetalert:library:1.3") {
        exclude (group = "com.android.support")
    }

    //implementation ("com.github.jumadeveloper:networkmanager:0.0.2")


    implementation ("com.squareup.retrofit2:retrofit:2.5.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")

    implementation ("com.squareup.okhttp3:okhttp:3.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.12.0")

    implementation ("com.google.code.gson:gson:2.8.5")

    implementation ("com.squareup.okio:okio:2.1.0")

}