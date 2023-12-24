plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

sourceSets {
    create("integration") {
        kotlin {
            compileClasspath += main.get().output + configurations["testRuntimeClasspath"]
            runtimeClasspath += output + compileClasspath + test.get().runtimeClasspath
        }
    }
}


dependencies {

    // LOG4J
    implementation("org.apache.logging.log4j:log4j-api:2.19.0")
    implementation("org.apache.logging.log4j:log4j-core:2.19.0")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.2.0")

    // FUEL API
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("com.github.kittinunf.fuel:fuel-coroutines:2.3.1")
    implementation("com.github.kittinunf.fuel:fuel-gson:2.3.1")

    // GSON API
    implementation("com.google.code.gson:gson:2.9.1")

    // Introspection Api Kotlin Module
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.20")

    // Coroutine Kotlin Module
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:31.0.1-jre")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest()
        }
        // Configure the built-in integration test suite
    }
}

application {
    // Define the main class for the application.
    mainClass.set("org.isen.newsapp.App")
}
