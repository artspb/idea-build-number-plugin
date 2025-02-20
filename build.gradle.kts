import org.jetbrains.intellij.platform.gradle.Constants
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.intellij.platform.gradle.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven("https://cache-redirector.jetbrains.com/plugins.gradle.org/m2")
    }
}

group = "me.artspb.idea.build.number.plugin"
version = "1.16"

val ideaVersion: String by extra

plugins {
    kotlin("jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.2.1"
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
        intellijIdeaCommunity(ideaVersion, useInstaller = false)
        javaCompiler(Constants.Constraints.PLATFORM_VERSION)
        testFramework(TestFrameworkType.Bundled)
    }

    testImplementation("org.opentest4j:opentest4j:1.3.0")
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
}

tasks.withType<PatchPluginXmlTask> {
    sinceBuild.set("252.6")
    untilBuild.set("252.*")
}
