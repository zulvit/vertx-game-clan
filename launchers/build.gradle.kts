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
    // https://mvnrepository.com/artifact/io.vertx/vertx-core
    implementation("io.vertx:vertx-core:4.3.6")
    implementation(project(":vertexses"))
    implementation(project(":models"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}