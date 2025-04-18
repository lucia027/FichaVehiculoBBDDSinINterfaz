plugins {
    kotlin("jvm") version "2.0.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    //Logger
    implementation("org.lighthousegames:logging:1.5.0")
    implementation("ch.qos.logback:logback-classic:1.5.12")
    implementation("org.mybatis:mybatis:3.5.19")

    //H2
    implementation("com.h2database:h2:2.3.232")
    implementation("org.xerial:sqlite-jdbc:3.49.1.0")

    //JDBI
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jdbi:jdbi3-core:3.48.0")
    implementation("org.jdbi:jdbi3-sqlobject:3.48.0")
    implementation("org.jdbi:jdbi3-kotlin:3.48.0")
    implementation("org.jdbi:jdbi3-kotlin-sqlobject:3.48.0")

    //MockK
    testImplementation("io.mockk:mockk:1.13.16")
    testImplementation(kotlin("test"))
    testImplementation("io.insert-koin:koin-test-junit5:4.0.2")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}