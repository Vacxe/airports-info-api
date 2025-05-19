val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    id("io.ktor.plugin") version "3.0.0"
    id("org.jetbrains.kotlinx.dataframe") version "0.14.1"
}

group = "io.github.vacxe.airportinfo"

application {
    mainClass.set("io.github.vacxe.airportinfo.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.jetbrains.kotlinx:dataframe:0.14.1")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
