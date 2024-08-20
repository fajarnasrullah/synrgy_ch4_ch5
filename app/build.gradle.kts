import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.libsDirectory

plugins {


    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    id("com.google.firebase.firebase-perf")



}

android {
    namespace = "com.jer.ch4_ch5"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jer.ch4_ch5"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            applicationIdSuffix = ".dev"
        }
//
//        create ("staging") {
//            isDebuggable = true
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//            applicationIdSuffix = ".stg"
//        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            applicationIdSuffix = ".release"

        }

//        create("beta") {
//            isDebuggable = true
//            isMinifyEnabled = true
//
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//            applicationIdSuffix = ".beta"
//        }
//
//        create("internal") {
//            isDebuggable = false
//            isMinifyEnabled = false
//            applicationIdSuffix = ".internal"
//        }
//
//        create("uat") {
//            isDebuggable = false
//            isMinifyEnabled = true
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//            applicationIdSuffix = ".uat"
//        }
    }

    flavorDimensions += "mode"
    productFlavors {
        create ("free") {
            dimension = "mode"
            applicationIdSuffix = ".free"

        }

        create ("paid") {
            dimension = "mode"
//            applicationIdSuffix = ".paid"

        }



    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    lint {
        baseline = file("lint.xml")
    }

}



dependencies {


    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":di"))


    implementation("com.google.firebase:firebase-perf:21.0.1")
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-analytics")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
//    testImplementation("org.mockito:mockito-core:4.4.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.3.1")
//    testImplementation ("io.mockk:mockk:1.13.8")
    testImplementation("junit:junit:4.13.2")

    debugImplementation ("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation ("com.github.chuckerteam.chucker:library-no-op:4.0.0")
    implementation ("io.github.chochanaresh:filepicker:0.2.5")
//    implementation(libs.filepicker)
//    implementation("com.github.TutorialsAndroid:FilePicker:v4.0.19")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("io.coil-kt:coil:2.6.0")
    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation ("io.insert-koin:koin-android")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("androidx.work:work-runtime-ktx:2.9.0")

    ksp("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-runtime:2.5.2")

    implementation("androidx.preference:preference:1.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}