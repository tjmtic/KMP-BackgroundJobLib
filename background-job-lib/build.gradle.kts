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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        // Kotlin Multiplatform plugin automatically creates publications for targets.
    }
    
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/tjmtic/KMP-BackgroundJobLib")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as String? ?: "USER_NOT_SET"
                password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key") as String? ?: "TOKEN_NOT_SET"
            }
        }
    }
}
