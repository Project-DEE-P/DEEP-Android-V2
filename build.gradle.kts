
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id ("com.android.library") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    kotlin("kapt") version "1.7.20" apply false

}



buildscript{
    dependencies {
        // other plugins...
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }
}


