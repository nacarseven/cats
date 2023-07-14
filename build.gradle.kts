// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version ("7.4.2") apply false
    id("com.android.library") version ("7.4.2") apply false
    id("org.jetbrains.kotlin.android") version ("1.7.20") apply false
    id("org.jetbrains.kotlin.jvm") version ("1.8.10") apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}