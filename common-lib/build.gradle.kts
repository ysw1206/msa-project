import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dep: Any) {}

if (gradle.startParameter.isOffline) {
    println("Offline build - skipped ${'$'}project.path")
} else {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
    }
}
