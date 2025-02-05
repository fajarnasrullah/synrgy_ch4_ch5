// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.android.library") version "8.5.1" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false


}

buildscript {
    dependencies {
        classpath ("com.google.firebase:perf-plugin:1.4.2")
        classpath ("com.google.gms:google-services:4.4.2")
    }
}