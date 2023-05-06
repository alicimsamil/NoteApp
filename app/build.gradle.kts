plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = Configs.compileSdkVersion
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    flavorDimensions.add("version")

    productFlavors {
        create("qa"){
            dimension = "version"
            manifestPlaceholders["appName"] = Configs.testAppName
            applicationId = Configs.qaApplicationId
            buildConfigField(
                "String",
                "DATABASE_NAME",
                "\"NOTE_APP_DB_QA\""
            )
            buildConfigField(
                "int",
                "DATABASE_VERSION_CODE",
                "1"
            )
        }

        create("prod"){
            dimension = "version"
            manifestPlaceholders["appName"] = Configs.prodAppName
            applicationId = Configs.applicationId
            buildConfigField(
                "String",
                "DATABASE_NAME",
                "\"NOTE_APP_DB\""
            )

            buildConfigField(
                "int",
                "DATABASE_VERSION_CODE",
                "1"
            )
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.appLibraries)
    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTestLibraries)
    kapt(Dependencies.kaptLibraries)
}

kapt {
    correctErrorTypes = true
}