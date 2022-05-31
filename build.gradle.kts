import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven("https://cache-redirector.jetbrains.com/plugins.gradle.org/m2")
    }
}

group = "me.artspb.idea.build.number.plugin"
version = "1.8"

val ideaVersion: String by extra
val pluginRepositoryToken: String by extra

plugins {
    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.intellij") version "1.6.0"
}

repositories {
    maven("https://cache-redirector.jetbrains.com/jcenter.bintray.com")
}

dependencies {
    compileOnly(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

intellij {
    version.set(ideaVersion)
}

tasks.withType<PatchPluginXmlTask> {
    sinceBuild.set("223.5")
    untilBuild.set("223.*")
}
