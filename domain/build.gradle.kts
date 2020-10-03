plugins {
    `java-library`
    id("kotlin")
}

dependencies {
    implementation(Libs.KOTLIN)
    implementation(Libs.CORE_KTX)
    implementation(Libs.APP_COMPAT)

    implementation(Libs.RX_ANDROID)
    implementation(Libs.RX_KOTLIN)

    //DI
    implementation(Libs.KOIN_VIEWMODEL)

    implementation(Libs.CONVERTER_GSON)
}