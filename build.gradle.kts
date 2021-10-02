import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories { 
		google()
		mavenCentral()
	}

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.netflix.nebula:gradle-aggregate-javadocs-plugin:2.2.+")
    }
}

plugins {
    kotlin("jvm") version "1.5.21"
	//kotlin("kapt") version "1.5.21"
    id("application")
    id("java")
    id("maven-publish")
    //id("com.github.ben-manes.versions")
    id("jacoco-aggregation")
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
    //id("org.jlleitschuh.gradle.ktlint") version "8.1.0"
	id("com.adarshr.test-logger") version "3.0.0"
}

apply {
    plugin("nebula-aggregate-javadocs")
}

group = "algorithms"
version = "0.0.1"

application {
    mainClass.set("algorithms.Application")
    applicationDefaultJvmArgs = listOf("-ea", "-Xms2g", "-Xmx4g")
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
}

repositories {
    google()
    mavenCentral()
}

micronaut {
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("algorithms.*")
    }
}

tasks.withType(KotlinCompile::class.java) {
    sourceCompatibility = "1.15"
    targetCompatibility = "1.15"
    kotlinOptions {
        jvmTarget = "1.15"
        apiVersion = "1.5"
        languageVersion = "1.5"
        allWarningsAsErrors = true
    }
}

val kotlinVersion: String = "1.5.21"

dependencies {
    val junitVersion = "5.5.2"
    implementation("io.micronaut:micronaut-runtime")
    implementation("javax.annotation:javax.annotation-api")	
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    //// Align versions of all Kotlin components
    //implementation(platform(kotlin("bom")))
    //// Use the Kotlin JDK 8 standard library.
    //implementation(kotlin("stdlib-jdk8"))
    //// Use the Kotlin test library.
    //testImplementation(kotlin("test"))
    // Use the Kotlin JUnit integration.
    //testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")    
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

    classpath = files(sourceSets.map { set -> set.map { it.output + it.compileClasspath } })
    setSource(sourceSets.map { set -> set.map { it.allJava } })
    setDestinationDir(file("$buildDir/docs/javadoc"))
}

/** Similar to {@link #javadocAggregate} but includes tests.
 * CI uses this target to validate javadoc (e.g. checking for broken links). */
val javadocAggregateIncludingTests by tasks.registering(Javadoc::class) {
    description = "Generates aggregate javadoc for all the artifacts"

    val allSourceSets = project.properties["sourceSets"] as SourceSetContainer
    val sourceSets = allSourceSets
        .mapNotNull { it.extensions.findByType<SourceSetContainer>() }
        .flatMap { listOf(it.named("main"), it.named("test")) }

    classpath = files(sourceSets.map { set -> set.map { it.output + it.compileClasspath } })
    setSource(sourceSets.map { set -> set.map { it.allJava } })
    setDestinationDir(file("$buildDir/docs/javadocAggregateIncludingTests"))
}

// KtLint: https://github.com/pinterest/ktlint
fun Project.getKtlintConfiguration(): Configuration {
    return configurations.findByName("ktlint") ?: configurations.create("ktlint") {
        val dependency = project.dependencies.create("com.pinterest:ktlint:0.41.0")
        dependencies.add(dependency)
    }
}
tasks.register("ktlint", JavaExec::class.java) {
    description = "Check Kotlin code style."
    group = "Verification"
    classpath = getKtlintConfiguration()
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("src/**/*.kt", "build.gradle.kts")
}
tasks.register("ktlintFormat", JavaExec::class.java) {
    description = "Fix Kotlin code style deviations."
    group = "formatting"
    classpath = getKtlintConfiguration()
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("-F", "src/**/*.kt", "build.gradle.kts")
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
