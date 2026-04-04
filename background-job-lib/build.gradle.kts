plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    `maven-publish`
}

group = "com.abyxcz.viewpoint.background"
version = "1.0.0"

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    
    // Placeholder for future iOS support
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    watchosArm32()
    watchosArm64()
    watchosSimulatorArm64()
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines.core)
        }
        
        androidMain.dependencies {
            implementation(libs.androidx.work.runtime)
        }
    }
}

android {
    namespace = "com.abyxcz.viewpoint.background"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
}
