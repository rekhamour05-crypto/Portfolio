plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("jacoco")
}

android {
    namespace = "com.example.rekha"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.rekha"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}
jacoco {
    toolVersion = "0.8.10"
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation("androidx.compose.material:material-icons-extended:1.7.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-compiler:2.47")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    // Retrofit + OkHttp + Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Testing
    // JUnit
    testImplementation("junit:junit:4.13.2")

// Truth (assertions)
    testImplementation("com.google.truth:truth:1.1.5")

// Mockk (mocking)
    testImplementation("io.mockk:mockk:1.13.5")

// Coroutines test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")

}
tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf(
        // Android auto-generated
        "**/R.class", "**/R$*.class",
        "**/BuildConfig.*", "**/Manifest*.*",
        // Hilt generated
        "**/*Hilt*.*", "**/hilt_**", "**/dagger/hilt/**",
        // Compose previews
        "**/*Preview*.*",
        // DI modules
        "**/*Module*.*",
        // Data binding / generated
        "**/*MapperImpl*.*",
    )

    val javaClasses = fileTree(
        "${buildDir}/intermediates/javac/debug/classes") {
        exclude(fileFilter)
    }

    val kotlinClasses = fileTree(
        "${buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    classDirectories.setFrom(files(javaClasses, kotlinClasses))
    sourceDirectories.setFrom(
        files("src/main/java", "src/main/kotlin")
    )
    executionData.setFrom(
        files("${buildDir}/jacoco/testDebugUnitTest.exec")
    )
}
tasks.register<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    dependsOn("testDebugUnitTest")

    violationRules {
        rule {
            limit {
                minimum = 0.90.toBigDecimal()  // 90% threshold
            }
        }
    }

    val fileFilter = listOf(
        "**/R.class", "**/R$*.class",
        "**/BuildConfig.*", "**/Manifest*.*",
        "**/*Hilt*.*", "**/hilt_**", "**/dagger/hilt/**",
        "**/*Preview*.*",
        "**/*Module*.*"
    )

    val javaClasses = fileTree("${buildDir}/intermediates/javac/debug/classes") {
        exclude(fileFilter)
    }
    val kotlinClasses = fileTree("${buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    classDirectories.setFrom(files(javaClasses, kotlinClasses))
    sourceDirectories.setFrom(
        files("src/main/java", "src/main/kotlin")
    )
    executionData.setFrom(
        files("${buildDir}/jacoco/testDebugUnitTest.exec")
    )
}
