plugins {
    id("java")
}

group = "ru.zulvit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    compileOnly("io.vertx:vertx-core:4.3.5")
    implementation("io.vertx:vertx-hazelcast:4.3.5")
    implementation(project(":models"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}