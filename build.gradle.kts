import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.31"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.31"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.31"
    id("org.springframework.boot") version "2.1.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

version = "1.1"

repositories {
    mavenCentral()
    maven {
        setUrl("https://dl.bintray.com/kotlin/kotlinx")
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx", "kotlinx-collections-immutable", "0.1")

    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("org.springframework.boot", "spring-boot-starter-security")
    implementation("org.springframework.boot", "spring-boot-devtools")
    implementation("org.thymeleaf.extras", "thymeleaf-extras-springsecurity5")
    implementation("com.h2database", "h2")

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}

tasks.withType<Jar> {
    baseName = "uilcompsciwebsite"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}