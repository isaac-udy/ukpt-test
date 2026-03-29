
plugins {
    // Empty root project - tasks will be added for pulling and testing the ukpt project
}

private val ukptWorkingDir = layout.projectDirectory.dir("ukpt-working")

val createUkptWorkspace by tasks.register("createUkptWorkspace") {
    doLast {
        val workingDir = ukptWorkingDir.asFile
        if (workingDir.exists()) {
            workingDir.deleteRecursively()
        }
        workingDir.mkdirs()

        exec {
            commandLine("git", "clone", "--branch", "main", "--single-branch", "--depth", "1",
                "https://github.com/isaac-udy/ukpt.git", workingDir.absolutePath)
        }

        exec {
            workingDir(workingDir)
            commandLine("git", "submodule", "update", "--init", "--recursive")
        }
    }
}

val createUkptTest by tasks.register("createUkptTest") {
    dependsOn(createUkptWorkspace)
    doLast {
        val testName = providers.gradleProperty("test").orNull
            ?: error("Missing required property: -Ptest=<test-name>")

        val testDir = layout.projectDirectory.dir("tests/$testName").asFile
        if (!testDir.exists()) {
            error("Test directory does not exist: ${testDir.absolutePath}")
        }

        val workingDir = ukptWorkingDir.asFile
        testDir.walkTopDown().filter { it.isFile }.forEach { sourceFile ->
            val relativePath = sourceFile.relativeTo(testDir)
            val targetFile = workingDir.resolve(relativePath.path)
            targetFile.parentFile.mkdirs()
            sourceFile.copyTo(targetFile, overwrite = true)
        }
    }
}

val runUkptTestServer by tasks.register("runUkptTestServer") {
    dependsOn(createUkptTest)
    doLast {
        exec {
            workingDir(ukptWorkingDir.asFile)
            commandLine("./gradlew", ":app:server:run")
        }
    }
}

val runUkptTestDesktop by tasks.register("runUkptTestDesktop") {
    dependsOn(createUkptTest)
    doLast {
        exec {
            workingDir(ukptWorkingDir.asFile)
            commandLine("./gradlew", ":app:client:run")
        }
    }
}