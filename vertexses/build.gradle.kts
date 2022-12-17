plugins {
    id("java")
}

group = "ru.zulvit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

    implementation(project(":models"))

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.1")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.1")
    // https://mvnrepository.com/artifact/io.vertx/vertx-core
    implementation("io.vertx:vertx-core:4.3.6")
    // https://mvnrepository.com/artifact/io.vertx/vertx-hazelcast
    implementation("io.vertx:vertx-hazelcast:4.3.6")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}