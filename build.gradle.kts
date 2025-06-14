if (gradle.startParameter.isOffline) {
    subprojects {
        tasks.register("build") {
            doLast { println("Offline mode - skipping build for ${'$'}project.path") }
        }
    }
} else {
    buildscript {
        repositories { mavenCentral() }
        dependencies {
            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
            classpath("org.springframework.boot:spring-boot-gradle-plugin:3.2.5")
            classpath("io.spring.gradle:dependency-management-plugin:1.1.4")
        }
    }

    allprojects {
        repositories { mavenCentral() }
    }

    subprojects {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        apply(plugin = "org.jetbrains.kotlin.plugin.spring")
        apply(plugin = "org.springframework.boot")
        apply(plugin = "io.spring.dependency-management")
    }
}
