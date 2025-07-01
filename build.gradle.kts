plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath("org.jetbrains.kotlin:kotlin-allopen:1.9.22")
    }
}


allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    if (gradle.startParameter.isOffline) {
        tasks.register("build") {
            doLast {
                println("Offline mode - skipping build for [36m${'$'}project.path[0m")
            }
        }
    }
}

// 모든 서비스를 병렬로 실행하는 task
task("startAllServices") {
    group = "application"
    description = "Start all microservices in parallel"
    
    doLast {
        println("🚀 Starting all microservices...")
        println("Use Ctrl+C to stop all services")
    }
}

// 개별 서비스 실행 task들
val serviceProjects = listOf("config-server", "discovery-server", "auth-service", "user-service", "public-service", "gateway-service")

serviceProjects.forEach { serviceName ->
    task("start${serviceName.split("-").joinToString("") { it.capitalize() }}") {
        group = "application"
        description = "Start $serviceName"
        dependsOn(":$serviceName:bootRun")
    }
}