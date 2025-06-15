pluginManagement {
    plugins {
        id("org.springframework.boot") version "3.1.5"
        id("io.spring.dependency-management") version "1.1.3"
        kotlin("jvm") version "1.9.10"
        kotlin("plugin.spring") version "1.9.10"
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "msa-project"
include("config-server", "discovery-server", "gateway-service", "auth-service", "user-service", "public-service", "common-lib")
