plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    defaultConfig {
        minSdk = AndroidConfigVersions.minSdkVersion
        targetSdk = AndroidConfigVersions.targetSdkVersion

        compileSdk = AndroidConfigVersions.compileSdkVersion
        buildToolsVersion = AndroidConfigVersions.buildToolsVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {

    // Square
    implementation(CoreDependencies.retrofit)
    implementation(CoreDependencies.retrofitSerialization)
    implementation(CoreDependencies.okhttp3)

    // JetBrains
    implementation(CoreDependencies.jsonSerialization)
    implementation(CoreDependencies.coreKtx)

    implementation(CoreDependencies.koin)
}