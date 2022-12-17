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
    // https://mvnrepository.com/artifact/io.vertx/vertx-core
    implementation("io.vertx:vertx-core:4.3.6")
    implementation("io.vertx:vertx-hazelcast:4.3.5")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}