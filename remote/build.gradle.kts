plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Apps.COMPILE_SDK)
    buildToolsVersion(Apps.BUILD_TOOL_VERSION)

    defaultConfig {
        minSdkVersion(Apps.MIN_SDK)
        targetSdkVersion(Apps.TARGET_SDK)
        versionCode = Apps.VERSION_CODE
        versionName = Apps.VERSION_NAME

        buildTypes {
            getByName(BuildTypes.DEBUG) {
                isDebuggable = true
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
                buildConfigField("String", "NAVER_CLIENT_ID", "\"cb6vgtmAXB0EgAX3Bbjv\"")
                buildConfigField("String", "NAVER_CLIENT_SECRET", "\"sP5Byv5r87\"")
                buildConfigField("String", "BASE_URL", "\"https://openapi.naver.com/\"")
            }
            getByName(BuildTypes.RELEASE) {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
                buildConfigField("String", "NAVER_CLIENT_ID", "\"cb6vgtmAXB0EgAX3Bbjv\"")
                buildConfigField("String", "NAVER_CLIENT_SECRET", "\"sP5Byv5r87\"")
                buildConfigField("String", "BASE_URL", "\"https://openapi.naver.com/\"")
            }
        }
    }

    compileOptions {
        sourceCompatibility = Apps.sourceCompatibility
        targetCompatibility = Apps.targetCompatibility
    }

    sourceSets {
        getByName(SourceSets.MAIN).java.srcDir("src/main/kotlin")
        getByName(SourceSets.TEST).java.srcDir("src/test/kotlin")
    }

    kotlinOptions {
        jvmTarget = Apps.JVM_TARGET
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.KOTLIN)
    implementation(Libs.CORE_KTX)
    implementation(Libs.APP_COMPAT)

    // RxJava
    implementation(Libs.RX_ANDROID)
    implementation(Libs.RX_KOTLIN)

    // Retrofit, OkHttp, Gson
    implementation(Libs.RETROFIT2)
    implementation(Libs.OKHTTP3)
    implementation(Libs.LOGGING_INTERCEPTORS)
    implementation(Libs.CONVERTER_GSON)
    implementation(Libs.ADAPTER_RXJAVA2)
    implementation(Libs.GSON)

    //DI
    implementation(Libs.KOIN_VIEWMODEL)

    //Coroutine
    implementation(Libs.COROUTINE_CORE)
    implementation(Libs.COROUTINE_ANDROID)

    // Logger
    implementation(Libs.LOGGER)

    //Test
    implementation(TestLibs.JUNIT)
    implementation(TestLibs.JUNIT_EXT)
    implementation(TestLibs.ESPRESSO)

    implementation(project(":data"))
    implementation(project(":domain"))
}
