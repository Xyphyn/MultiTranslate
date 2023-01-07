
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.31"

    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:30.1.1-jre")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    api("org.apache.commons:commons-math3:3.6.1")
}
