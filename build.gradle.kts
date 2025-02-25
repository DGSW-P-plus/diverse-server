import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    val kotlinVersion = "2.0.0"
    kotlin("jvm") version kotlinVersion

    kotlin("kapt") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion

    application

    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

repositories {
    mavenCentral()
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

    group = "dev.jombi"
    version = "0.0.1-SNAPSHOT"

    dependencyManagement {
        dependencies {
            dependencySet("io.jsonwebtoken:0.12.6") {
                entry("jjwt-api")
                entry("jjwt-impl")
                entry("jjwt-jackson")
            }

            dependencySet("com.querydsl:5.1.0") {
                entry("querydsl-core")
                entry("querydsl-jpa")
                entry("querydsl-apt")
            }
        }
    }

    repositories {
        mavenCentral()
    }

    springBoot {
        mainClass.set("dev.jombi.diverse.DiverseApplicationKt")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlin {
            jvmToolchain(17)
            compilerOptions {
                languageVersion.set(KotlinVersion.KOTLIN_2_0)
                apiVersion.set(KotlinVersion.KOTLIN_2_0)
                freeCompilerArgs.addAll("-Xjsr305=strict")
            }
        }
    }

    tasks.bootJar {
        enabled = true
    }

    dependencies {
        /// SPRING BOOT
        api("org.springframework.boot:spring-boot-starter-web")

        /// KOTLIN
        implementation(kotlin("reflect"))

        /// TEST
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")

        testImplementation(kotlin("test-junit5"))
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

subprojects {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-websocket")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        implementation("org.mapstruct:mapstruct:1.5.5.Final")
        kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    }
}

dependencies {
    implementation(project(":api"))
    implementation(project(":business"))
    implementation(project(":core"))
    implementation(project(":infra"))

    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.jar {
    enabled = false
}
