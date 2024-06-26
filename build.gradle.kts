plugins {
	java
	jacoco
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.sonarqube") version "4.0.0.2929"
}

group = "com.adpro"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
        toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val seleniumJavaVersion = "4.14.1"
val seleniumJupiterVersion = "5.0.1"
val webdrivermanagerVersion = "5.6.3"
val junitJupiterVersion = "5.9.1"

dependencies {
	implementation("org.yaml:snakeyaml:1.29")
	implementation("org.apache.commons:commons-pool2:2.11.1")
	implementation ("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.github.javafaker:javafaker:1.0.2")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.2")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")
	implementation("io.micrometer:micrometer-registry-prometheus")


	compileOnly("org.projectlombok:lombok")
	
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.mockito:mockito-core")
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("junit:junit")
	testImplementation("io.github.bonigarcia:selenium-jupiter:$seleniumJupiterVersion")
	testImplementation("io.github.bonigarcia:webdrivermanager:$webdrivermanagerVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

sonar {
	properties {
		property("sonar.projectKey", "sonar.projectKey=Adpro-C4_microservice-katalog")
		property("sonar.organization", "adpro-c4")
		property("sonar.host.url", "https://sonarcloud.io")
	}
}

tasks.register<Test>("unitTest") {
	description = "Runs unit tests."
	group = "verification"

	filter {
		excludeTestsMatching("*FunctionalTest")
	}
}

tasks.register<Test>("functionalTest") {
	description = "Runs functional tests."
	group = "verification"

	filter {
		includeTestsMatching("*FunctionalTest")
	}
}

tasks.withType<Test>().configureEach {
	useJUnitPlatform()
}

tasks.test{
	filter{
		excludeTestsMatching("*FunctionalTest")
	}
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport{
	dependsOn(tasks.test)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required = true
		html.required = true
	}
}
