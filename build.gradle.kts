// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra["kotlin_version"] = "1.5.31"
    extra["coroutines_version"] = "1.6.1"
    extra["okhttp_version"] = "4.9.3"
    extra["retrofit_version"] = "2.9.0"
    extra["hiltViewModelVersion"] = "1.0.0-alpha03"
    extra["hilt_version"] = "2.47"
    extra["room_version"] = "2.5.2"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val kotlin_version: String by project
        val hilt_version: String by project

        classpath ("com.android.tools.build:gradle:4.2.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id ("com.android.application") version "8.1.2" apply false
    id ("com.android.library") version "8.1.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.10" apply false
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
