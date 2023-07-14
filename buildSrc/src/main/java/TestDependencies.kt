import versions.TestVersions
import versions.UiVersions
import versions.CoreVersions

object TestDependencies {
    val junit by lazy { "junit:junit:${TestVersions.junit}" }
    val androidxJunit by lazy { "androidx.test.ext:junit:${TestVersions.androidxJunit}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${TestVersions.espressoCore}" }
    val mockk by lazy { "io.mockk:mockk:${TestVersions.mockk}" }
    val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${CoreVersions.coroutines}" }
    val lifecycleViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${TestVersions.lifecycleviewmodel}" }
    val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${TestVersions.lifecycleruntime}" }
    val archCoreTesting by lazy { "androidx.arch.core:core-testing:${TestVersions.archCore}" }
    val turbine by lazy { "app.cash.turbine:turbine:${TestVersions.turbine}" }
}