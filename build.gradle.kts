plugins {
    kotlin("jvm") version "2.3.21"
    `maven-publish`
}

group = "com.github.vectura"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.github.vectura.backend:parser:dev")

    implementation("org.apache.commons:commons-csv:1.14.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.8.0")
    implementation("commons-io:commons-io:2.15.1")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "gtfs"
            from(components["java"])
        }
    }
}