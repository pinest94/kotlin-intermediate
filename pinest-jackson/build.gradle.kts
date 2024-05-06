plugins {
    id("kotlin")
}

group = "com.pinest"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}