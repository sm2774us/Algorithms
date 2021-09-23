import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("io.micronaut.application")
    id("org.jlleitschuh.gradle.ktlint") version "8.1.0"
	id("com.adarshr.test-logger") version "3.0.0"
    kotlin("jvm") version "1.5.31"
	kotlin("kapt") version "1.5.31"

    // Apply the java-library plugin for API and implementation seaparation.
    `java-library`
}

group = "algorithms"
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
    google()
    mavenCentral()
    //jcenter()
}

micronaut {
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("algorithms.*")
    }
}

dependencies {
    val junitVersion = "5.5.2"
    implementation("io.micronaut:micronaut-runtime")
    implementation("javax.annotation:javax.annotation-api")	
    // Align versions of all Kotlin components
    implementation(platform(kotlin("bom")))
    // Use the Kotlin JDK 8 standard library.
    implementation(kotlin("stdlib-jdk8"))
    // Use the Kotlin test library.
    testImplementation(kotlin("test"))
    // Use the Kotlin JUnit integration.
    testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

application {
    mainClass.set("algorithms.Application")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        showStandardStreams = true
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "15"
    }
}