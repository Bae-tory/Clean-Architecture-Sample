buildscript {
    repositories {
        google()
        jcenter()

        dependencies {
            classpath("com.android.tools.build:gradle:${Versions.GRADLE}")
            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
            classpath("com.google.gms:google-services:${Versions.GOOGLE_SERVICE}")
//            classpath("com.google.firebase:firebase-crashlytics-gradle:${Versions.FIREBASE_CRASHLYTICS_GRADLE}")
        }
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://maven.google.com/") }
        maven { url = uri("https://jitpack.io") }
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
