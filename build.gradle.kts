import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.intellij.platform.gradle.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven("https://cache-redirector.jetbrains.com/plugins.gradle.org/m2")
    }
}

group = "me.artspb.idea.build.number.plugin"
version = "1.14"

val ideaVersion: String by extra
val pluginRepositoryToken: String by extra

plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.intellij.platform") version "2.0.0-beta8"
}

repositories {
    maven("https://cache-redirector.jetbrains.com/jcenter.bintray.com")
    intellijPlatform {
        defaultRepositories()
        jetbrainsRuntime()
        nightly()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity(ideaVersion)
        jetbrainsRuntime()
        instrumentationTools()

        testFramework(TestFrameworkType.Bundled)
    }

    testImplementation("org.opentest4j:opentest4j:1.3.0")
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<PatchPluginXmlTask> {
    sinceBuild.set("232.109")
    untilBuild.set("243.*")
}
