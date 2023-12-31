plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version ("1.6.21")
}

android {
    namespace = "com.nacarseven.cats"
    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.nacarseven.cats"
        minSdk = AndroidConfigVersions.minSdkVersion
        targetSdk = AndroidConfigVersions.targetSdkVersion
        versionCode = AndroidConfigVersions.defaultVersionCode
        versionName = AndroidConfigVersions.defaultVersionName

        compileSdk = AndroidConfigVersions.compileSdkVersion

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
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
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

    // Modules
    implementation(project(versions.LibModules.network))
    implementation(project(versions.LibModules.common))
    implementation(CoreDependencies.coreKtx)
    implementation(UiDependencies.appcompat)
    implementation(UiDependencies.material)
    implementation(UiDependencies.constraintLayout)
    implementation(UiDependencies.glide)

    // Square
    implementation(CoreDependencies.retrofit)
    implementation(CoreDependencies.retrofitSerialization)
    implementation(CoreDependencies.okhttp3)

    // JetBrains
    implementation(CoreDependencies.jsonSerialization)
    implementation(CoreDependencies.coroutines)

    // Koin
    implementation(CoreDependencies.koin)

    // Test
    implementation(TestDependencies.lifecycleViewModel)
    implementation(TestDependencies.lifecycleRuntime)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutinesTest)
    testImplementation(TestDependencies.archCoreTesting)
    testImplementation(TestDependencies.turbine)
    androidTestImplementation(TestDependencies.androidxJunit)
    androidTestImplementation(TestDependencies.espressoCore)

}