import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven("https://cache-redirector.jetbrains.com/plugins.gradle.org/m2")
    }
}

group = "me.artspb.idea.build.number.plugin"
version = "1.10.1"

val ideaVersion: String by extra
val pluginRepositoryToken: String by extra

plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.intellij") version "1.13.2"
}

repositories {
    maven("https://cache-redirector.jetbrains.com/jcenter.bintray.com")
}

dependencies {
    compileOnly(kotlin("stdlib-jdk8"))
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

intellij {
    version.set(ideaVersion)
}

tasks.withType<PatchPluginXmlTask> {
    sinceBuild.set("232.109")
    untilBuild.set("232.*")
}
