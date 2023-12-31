import versions.UiVersions

object UiDependencies {
    val appcompat by lazy { "androidx.appcompat:appcompat:${UiVersions.appcompat}" }
    val material by lazy { "com.google.android.material:material:${UiVersions.material}" }
    val constraintLayout by lazy {"androidx.constraintlayout:constraintlayout:${UiVersions.constraintLayout}"}
    val glide by lazy { "com.github.bumptech.glide:glide:${UiVersions.glide}"}
}