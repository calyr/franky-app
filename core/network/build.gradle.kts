plugins {
    alias(libs.plugins.ucb.android.library)
    alias(libs.plugins.ksp)
//    alias(libs.plugins.kapt)
}

android {
    namespace = "com.calyr.network"

}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.converter.moshi)
    implementation(project(":core:model"))
    ksp(libs.moshi.kapt)
}