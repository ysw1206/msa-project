plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
