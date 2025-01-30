plugins {
    id("java")
    id("application")
}

group = "io.github.nevemlaci"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.json:json:20240303")
    implementation("com.formdev:flatlaf:3.5.2")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("io.github.nevemlaci.Main")
}