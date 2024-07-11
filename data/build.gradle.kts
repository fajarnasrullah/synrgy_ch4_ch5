plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")

}

android {
    namespace = "com.jer.ch4_ch5.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {

        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL_ART", "\"https://www.rijksmuseum.nl/\"")
            buildConfigField("String", "BASE_URL_REQRES", "\"https://reqres.in/api/\"")

        }

        create ("staging") {
            isMinifyEnabled = true
            buildConfigField("String", "BASE_URL_ART", "\"https://www.rijksmuseum.nl/\"")
            buildConfigField("String", "BASE_URL_REQRES", "\"https://reqres.in/api/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = true
            buildConfigField("String", "BASE_URL_ART", "\"https://www.rijksmuseum.nl/\"")
            buildConfigField("String", "BASE_URL_REQRES", "\"https://reqres.in/api/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("beta") {
            isMinifyEnabled = true
            buildConfigField("String", "BASE_URL_ART", "\"https://www.rijksmuseum.nl/\"")
            buildConfigField("String", "BASE_URL_REQRES", "\"https://reqres.in/api/\"")

        }

        create("internal") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL_ART", "\"https://www.rijksmuseum.nl/\"")
            buildConfigField("String", "BASE_URL_REQRES", "\"https://reqres.in/api/\"")
        }

        create("uat") {
            isMinifyEnabled = true
            buildConfigField("String", "BASE_URL_ART", "\"https://www.rijksmuseum.nl/\"")
            buildConfigField("String", "BASE_URL_REQRES", "\"https://reqres.in/api/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }


    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
//    testImplementation("org.mockito:mockito-core:4.4.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.3.1")
//    testImplementation ("io.mockk:mockk:1.13.8")
    testImplementation("junit:junit:4.13.2")


    implementation ("androidx.work:work-runtime-ktx:2.7.1")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    ksp("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-runtime:2.5.2")
    implementation(project(":domain"))
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}