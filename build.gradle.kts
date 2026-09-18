@file:Suppress("UnstableApiUsage")

plugins {
    `item-dfu`
    id("net.fabricmc.fabric-loom")
}

dependencies {
    minecraft(versionedCatalog["minecraft"])
}

val mcVersion = stonecutter.current.version.replace(".", "")
loom {
    runConfigs["client"].apply {
        ideConfigGenerated(true)
        runDir = "../../run"
        vmArg("-Dfabric.modsFolder=" + '"' + rootProject.projectDir.resolve("run/${mcVersion}Mods").absolutePath + '"')
    }
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
    withSourcesJar()
}

if (providers.gradleProperty("jitpack").isPresent
    || providers.gradleProperty("group").orElse("").get().startsWith("com.github")
) {
    // JitPack publishes a plain jar: drop the dev classifiers so the standard
    // Maven coordinates (no classifier, "sources") resolve for consumers.
    // Registered after `item-dfu`'s own afterEvaluate, so this wins.
    afterEvaluate {
        tasks.named<Jar>("jar") {
            archiveClassifier = ""
        }

        tasks.named<Jar>("sourcesJar") {
            archiveClassifier = "sources"
        }
    }
}