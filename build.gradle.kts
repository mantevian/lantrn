import gg.jte.ContentType
import java.nio.file.Paths

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val jte_version: String by project

plugins {
	kotlin("jvm") version "1.9.23"
	id("io.ktor.plugin") version "2.3.10"
	id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
	java
	id("gg.jte.gradle") version "3.1.12"
}

group = "xyz.mantevian.lantrn"
version = "0.0.1"

application {
	mainClass.set("io.ktor.server.netty.EngineMain")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	mavenCentral()
	maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
	implementation("org.jetbrains:markdown:0.7.2")
	implementation("io.ktor:ktor-server-core-jvm")
	implementation("io.ktor:ktor-server-host-common-jvm")
	implementation("io.ktor:ktor-server-status-pages-jvm")
	implementation("io.ktor:ktor-server-content-negotiation-jvm")
	implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
	implementation("io.ktor:ktor-server-jte:$ktor_version")
	implementation("io.ktor:ktor-server-caching-headers:$ktor_version")
	implementation("gg.jte:jte:$jte_version")
	implementation("io.ktor:ktor-server-compression:$ktor_version")
	implementation("io.ktor:ktor-server-netty-jvm")
	implementation("ch.qos.logback:logback-classic:$logback_version")
	implementation("io.ktor:ktor-server-config-yaml:2.3.10")
	testImplementation("io.ktor:ktor-server-tests-jvm")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

jte {
	generate()
}

sourceSets.main {
	java.srcDirs("src/main/kotlin", "build/jte-classes")
}

tasks {
	generateJte {
		sourceDirectory = Paths.get("src", "main", "resources", "templates")
		contentType = ContentType.Html
		targetDirectory = Paths.get("build", "jte-classes")
	}
}