plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("dagger.hilt.android.plugin")
//    id("com.google.gms.google-services")
//    id("com.google.firebase.crashlytics")
}

android {
    compileSdkVersion(Apps.COMPILE_SDK)
    buildToolsVersion(Apps.BUILD_TOOL_VERSION)

    defaultConfig {
        applicationId = Apps.ID
        minSdkVersion(Apps.MIN_SDK)
        targetSdkVersion(Apps.TARGET_SDK)
        versionCode = Apps.VERSION_CODE
        versionName = Apps.VERSION_NAME
        testInstrumentationRunner = Apps.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildTypes.DEBUG) {
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = Apps.sourceCompatibility
        targetCompatibility = Apps.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = Apps.JVM_TARGET
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Kotlin
    implementation(Libs.KOTLIN)

    //Standard
    implementation(Libs.APP_COMPAT)
    implementation(Libs.CORE_KTX)
    implementation(Libs.MATERIAL)
    implementation(Libs.CONSTRAINT_LAYOUT)

    // Room
    implementation(Libs.ROOM_RUNTIME)
    implementation(Libs.ROOM_KTX)
    implementation(Libs.ROOM_RXJAVA2)
    kapt(Libs.ROOM_COMPILER)
    implementation(Libs.ROOM_ARC_PERSISTENCE_RUNTIME)
    kapt(Libs.ROOM_ARC_PERSISTENCE_COMPILER)

    //Retrofit
    implementation(Libs.RETROFIT2)
    implementation(Libs.CONVERTER_GSON)
    implementation(Libs.ADAPTER_RXJAVA2)
    implementation(Libs.OKHTTP3)
    implementation(Libs.LOGGING_INTERCEPTORS)

    //Rx
    implementation(Libs.RX_ANDROID)
    implementation(Libs.RX_KOTLIN)

    //Image
    implementation(Libs.PICASSO)
    implementation(Libs.GLIDE)
    implementation(Libs.GLIDE_COMPILER)
    implementation(Libs.GLIDE_OKHTTP_INTERGRATION)

    //DI
    implementation(Libs.HILT_ANDROID)
    kapt(Libs.HILT_ANDROID_COMPILER)
    implementation(Libs.HILT_VIEWMODEL)
    implementation(Libs.HILT_COMMON)
    kapt(Libs.HILT_COMPILER)
    implementation(Libs.FRAGMENT_KTX)

    //Firebase
    implementation(Libs.FIREBASE_MESSAGING)
    implementation(Libs.FIREBASE_CORE)

    //MP
    implementation(Libs.LOGGER)

    //Coroutine
    implementation(Libs.COROUTINE_CORE)
    implementation(Libs.COROUTINE_ANDROID)
    implementation(Libs.LIFECYCLE_VIEWMODEL_KTX)

    //Test
    implementation(TestLibs.JUNIT)
    implementation(TestLibs.JUNIT_EXT)
    implementation(TestLibs.ESPRESSO)
    implementation(Libs.FIREBASE_ANALYTICS)
    implementation(Libs.FIREBASE_CRASHLYTICS)

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":local"))
    implementation(project(":remote"))
    implementation(project(":content"))
}