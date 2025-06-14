import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dep: Any) {}
fun DependencyHandler.testImplementation(dep: Any) {}

if (gradle.startParameter.isOffline) {
    println("Offline build - skipped ${'$'}project.path")
} else {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation(project(":common-lib"))
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}
