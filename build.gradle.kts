import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories { jcenter() }

    dependencies {
        classpath("com.netflix.nebula:gradle-aggregate-javadocs-plugin:2.2.+")
    }
}

plugins {
    //id("com.github.ben-manes.versions")
    id("jacoco-aggregation")
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
    //id("org.jlleitschuh.gradle.ktlint") version "8.1.0"
	id("com.adarshr.test-logger") version "3.0.0"
    kotlin("jvm") version "1.5.31"
	//kotlin("kapt") version "1.5.31"

    // Apply the java-library plugin for API and implementation seaparation.
    `java-library`
}

apply {
    plugin("nebula-aggregate-javadocs")
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
    //testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    //testImplementation("io.mockk:mockk:1.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

val javadocAggregate by tasks.registering(Javadoc::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Generates aggregate javadoc for all the artifacts"

    val allSourceSets = project.properties["sourceSets"] as SourceSetContainer
	val sourceSets = allSourceSets
        .mapNotNull { it.extensions.findByType<SourceSetContainer>() }
        .map { it.named("main") }
    //val sourceSets = subprojects
    //    .mapNotNull { it.extensions.findByType<SourceSetContainer>() }
    //    .map { it.named("main") }

    classpath = files(sourceSets.map { set -> set.map { it.output + it.compileClasspath } })
    setSource(sourceSets.map { set -> set.map { it.allJava } })
    setDestinationDir(file("$buildDir/docs/javadoc"))
}

/** Similar to {@link #javadocAggregate} but includes tests.
 * CI uses this target to validate javadoc (e.g. checking for broken links). */
val javadocAggregateIncludingTests by tasks.registering(Javadoc::class) {
    description = "Generates aggregate javadoc for all the artifacts"

    val sourceSets = project.properties["sourceSets"] as SourceSetContainer
    //val sourceSets = subprojects
    //    .mapNotNull { it.extensions.findByType<SourceSetContainer>() }
    //    .flatMap { listOf(it.named("main"), it.named("test")) }

    classpath = files(sourceSets.map { set -> set.map { it.output + it.compileClasspath } })
    setSource(sourceSets.map { set -> set.map { it.allJava } })
    setDestinationDir(file("$buildDir/docs/javadocAggregateIncludingTests"))
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