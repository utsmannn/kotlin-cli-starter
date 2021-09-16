import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "com.utsman"
version = "1.0.0"
val packageName = "$group.cliapp"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Clikt library
    implementation("com.github.ajalt.clikt:clikt:3.2.0")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "$packageName.ApplicationKt"
    }

    tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.register<Copy>("buildScript") {
    dependsOn("build")
    val jarFile = "$buildDir/libs/${project.name}-${project.version}.jar"
    val intoFile = "$rootDir/dist/libs"
    from(jarFile)
    into(intoFile)

    doLast {
        val file = File("$rootDir/dist", project.name)
        file.writeText("""
            #/usr/bin
            java -jar ${projectDir.absolutePath}/dist/libs/${project.name}-${project.version}.jar ${'$'}@
        """.trimIndent())

        exec {
            commandLine("chmod", "+x", file.absolutePath)
        }
    }

    val scriptLocation = "${projectDir.absolutePath}/dist/${project.name}"
    System.out.println("Script location created on $scriptLocation")
}