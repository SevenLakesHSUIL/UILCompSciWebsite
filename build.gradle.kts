import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.50"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.50"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.50"
    id("org.springframework.boot") version "2.1.9.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("com.diffplug.gradle.spotless") version "3.25.0"
}

version = "1.2"

repositories {
    mavenCentral()
    maven {
        setUrl("https://dl.bintray.com/kotlin/kotlinx")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx", "kotlinx-collections-immutable", "0.2")

    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("org.springframework.boot", "spring-boot-starter-security")
    implementation("org.thymeleaf.extras", "thymeleaf-extras-springsecurity5")
    runtimeOnly("com.h2database", "h2")

    annotationProcessor("org.springframework.boot", "spring-boot-configuration-processor")
}

spotless {
    kotlin {
        ktlint("0.33.0")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
    kotlinGradle {
        ktlint("0.33.0")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Wrapper>().configureEach {
    gradleVersion = "5.5.1"
}