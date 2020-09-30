import org.gradle.api.JavaVersion

object BuildTypes {
    const val DEBUG: String = "debug"
    const val RELEASE: String = "release"
}

object SourceSets {
    const val MAIN: String = "main"
    const val TEST: String = "test"
}

object Apps {
    val sourceCompatibility: JavaVersion = JavaVersion.VERSION_1_8
    val targetCompatibility: JavaVersion = JavaVersion.VERSION_1_8
    const val JVM_TARGET: String = "1.8"
    const val COMPILE_SDK: Int = 29
    const val ID: String = "com.project.sungjae"
    const val MIN_SDK: Int = 23
    const val TARGET_SDK: Int = 29
    const val BUILD_TOOL_VERSION: String = "29.0.3"
    const val VERSION_CODE: Int = 1
    const val VERSION_NAME: String = "1.0.0"
    const val TEST_INSTRUMENTATION_RUNNER: String = "androidx.test.runner.AndroidJUnitRunner"
}

object Versions {
    const val R8: String = "r8:2.0.88"
    const val GRADLE: String = "4.0.0"
    const val KOTLIN: String = "1.3.72"

    const val CONSTRAINT_LAYOUT: String = "2.0.0-beta7"
    const val CORE_KTX: String = "1.3.0"
    const val APP_COMPAT: String = "1.1.0"
    const val ANNOTATION: String = "1.1.0"
    const val MATERIAL: String = "1.3.0-alpha01"
    const val CARDVIEW: String = "1.0.0"
    const val RECYCLERVIEW: String = "1.1.0"
    const val RETROFIT2: String = "2.7.0"
    const val OKHTTP3: String = "4.4.1"
    const val CONVERTER_GSON: String = "2.7.2"
    const val ADAPTER_RX_JAVA2: String = "2.7.2"
    const val GSON: String = "2.7.2"
    const val RX_JAVA2: String = "2.2.13"
    const val RX_ANDROID: String = "2.1.1"
    const val RX_KOTLIN: String = "2.4.0"
    const val LIFECYCLE_VIEWMODEL_KTX = "2.2.0"
    const val ROOM: String = "2.2.5"
    const val PICASSO: String = "2.71828"
    const val GLIDE: String = "4.11.0"
    const val GLIDE_OKHTTP_INTERGRATION: String = "4.9.0"
    const val KOIN: String = "2.1.5"
    const val HILT: String = "2.28.3-alpha"
    const val HILT_LIFECYCLE: String = "1.0.0-alpha01"
    const val LOGGER: String = "2.2.0"
    const val FIREBASE_MESSAGING: String = "20.2.1"
    const val FIREBASE_CORE: String = "17.4.3"
    const val FIREBASE_ANALYTICS = "17.4.4"
    const val FIREBASE_CRASHLYTICS = "17.1.1"
    const val FIREBASE_CRASHLYTICS_GRADLE = "2.2.0"
    const val GOOGLE_SERVICE: String = "4.3.3"
    const val GOOGLE_MAP: String = "17.0.0"
    const val COROUTINE: String = "1.3.7"
    const val JUNIT: String = "4.12"
    const val JUNIT_EXT: String = "1.1.1"
    const val ESPRESSO: String = "3.2.0"
}

object Libs {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val ANNOTATION = "androidx.annotation:annotation:${Versions.ANNOTATION}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CARDVIEW = "androidx.cardview:cardview:${Versions.CARDVIEW}"
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW}"
    const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
    const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP3}"
    const val LOGGING_INTERCEPTORS = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP3}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.CONVERTER_GSON}"
    const val ADAPTER_RXJAVA2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.ADAPTER_RX_JAVA2}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    const val RX_JAVA2 = "io.reactivex.rxjava2:rxjava:${Versions.RX_JAVA2}"
    const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.RX_ANDROID}"
    const val RX_KOTLIN = "io.reactivex.rxjava2:rxkotlin:${Versions.RX_KOTLIN}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_RXJAVA2 = "androidx.room:room-rxjava2:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val PICASSO = "com.squareup.picasso:picasso:${Versions.PICASSO}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    const val GLIDE_OKHTTP_INTERGRATION = "com.github.bumptech.glide:okhttp3-integration:${Versions.GLIDE_OKHTTP_INTERGRATION}"
    const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE}"
    const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}"
    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VIEWMODEL_KTX}"
    const val KOIN = "org.koin:koin-android:${Versions.KOIN}"
    const val KOIN_SCOPE = "org.koin:koin-android-scope:${Versions.KOIN}"
    const val KOIN_VIEWMODEL = "org.koin:koin-android-viewmodel:${Versions.KOIN}"
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    const val HILT_COMMON = "androidx.hilt:hilt-common:${Versions.HILT_LIFECYCLE}"
    const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_LIFECYCLE}"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${Versions.HILT_LIFECYCLE}"
    const val FIREBASE_MESSAGING = "com.google.firebase:firebase-messaging:${Versions.FIREBASE_MESSAGING}"
    const val FIREBASE_CORE = "com.google.firebase:firebase-core:${Versions.FIREBASE_CORE}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics:${Versions.FIREBASE_ANALYTICS}"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics:${Versions.FIREBASE_CRASHLYTICS}"
    const val LOGGER = "com.orhanobut:logger:${Versions.LOGGER}"
    const val GOOGLE_MAPS = "com.google.android.gms:play-services-maps:${Versions.GOOGLE_MAP}"
    const val GOOGLE_LOCATION = "com.google.android.gms:play-services-location:${Versions.GOOGLE_MAP}"
}

object TestLibs {
    const val KOIN_TEST = "org.koin:koin-test:${Versions.KOIN}"
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
}