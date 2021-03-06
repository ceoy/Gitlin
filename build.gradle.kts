import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.11")

    id("org.jlleitschuh.gradle.ktlint").version("6.3.1")

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/File repository here.
    jcenter()
    maven("https://jitpack.io")
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.github.ajalt:clikt:1.6.0")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // Speck for testing
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.1")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.1")

    // spek requires kotlin-reflect, can be omitted if already in the classpath
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect")
}

application {
    // Define the main class for the application.
    mainClassName = "ch.tim.GitlinKt"
}

tasks {
    test {
        useJUnitPlatform {
            includeEngines = setOf("spek2")
        }
    }
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


ktlint {
    reporters.set(setOf(ReporterType.CHECKSTYLE, ReporterType.PLAIN))

    filter {
        // exclude tests
        exclude("**/*Spec.kt")
    }
}
