import gg.meza.stonecraft.mod
import io.github.cdimascio.dotenv.Dotenv

buildscript {
    dependencies {
        classpath("io.github.cdimascio:dotenv-java:3.0.0")
    }
}

val dotenv = Dotenv.configure()
    .directory(rootDir.absolutePath)
    .ignoreIfMissing()
    .load()

plugins {
    id("gg.meza.stonecraft")
}

modSettings {
    clientOptions {
        fov = 0
        guiScale = 3
        narrator = false
        darkBackground = false
        musicVolume = 0.0
        additionalLines = mapOf(
            "mouseSensitivity" to "0.7",
            "renderClouds" to "false",
            "renderDistance" to "12",
            "simulationDistance" to "5",
            "operatorItemsTab" to "true",
            "key_key.togglePerspective" to "key.keyboard.keypad.5",
        )
    }
}

dependencies {
    // modImplementation("gg.meza:meza_core-${mod.loader}:${mod.prop("meza_core_version")}+${stonecutter.current.version}")
    // include("gg.meza:meza_core-${mod.loader}:${mod.prop("meza_core_version")}+${stonecutter.current.version}")

    if (mod.hasProp("architectury_api_version")) {
        modImplementation("dev.architectury:architectury-${mod.loader}:${mod.prop("architectury_api_version")}")
    }

    if (mod.hasProp("kage_library_version")) {
        modImplementation(files("../../../kage-library/versions/${mod.minecraftVersion}-${mod.loader}/build/libs/kagelibrary-${mod.loader}-${mod.prop("kage_library_version")}+mc${mod.minecraftVersion}.jar"))
    }

    // if (mod.isNeoforge) {
    //     modApi("me.shedaniel.cloth:cloth-config-neoforge:${mod.prop("cloth_version")}")
    // }
    // if (mod.isFabric) {
    //     modApi("me.shedaniel.cloth:cloth-config-fabric:${mod.prop("cloth_version")}") {
    //         exclude(group = "net.fabricmc.fabric-api")
    //     }
    //     if (mod.hasProp("modmenu_version")) {
    //         modApi("com.terraformersmc:modmenu:${mod.prop("modmenu_version")}")
    //     }
    // }
}

publishMods {
    type = STABLE
    dryRun = true // Disable to publish live

    changelog = """
        - updated mod id and imports to `enderpearlsreworked`
        - updated metadata and pack image
        - added modmenu support
    """.trimIndent()

    modrinth {
        // General
        accessToken = dotenv["MODRINTH_TOKEN"]
        projectId = "gC8sWa3D"
        // projectDescription = providers.fileContents(layout.projectDirectory.file("README.md")).asText
        minecraftVersions.add(mod.minecraftVersion)
        modLoaders.add(mod.loader)

        // Dependencies
        if (mod.isFabric) requires("fabric-api")
        if (mod.hasProp("architectury_api_version")) requires("architectury-api")
        if (mod.hasProp("kage_library_version")) requires("kage-library")
        if (mod.isForgeLike) optional("better-modlist")
        if (mod.isFabric) optional("modmenu")
        optional("enderman-overhaul")

        // Discord
        // Optionally set the announcement title used by the discord publisher
        // announcementTitle = "Download from Modrinth"
    }

    curseforge {
        // General
        accessToken = dotenv["CURSEFORGE_TOKEN"]
        projectId = "1451776"
        minecraftVersions.add(mod.minecraftVersion)
        modLoaders.add(mod.loader)
        javaVersions.add(JavaVersion.VERSION_21)
        clientRequired = true
        serverRequired = true

        // Dependencies
        if (mod.isFabric) requires("fabric-api")
        if (mod.hasProp("architectury_api_version")) requires("architectury-api")
        if (mod.hasProp("kage_library_version")) requires("kage-library")
        if (mod.isForgeLike) optional("better-modlist-neoforge")
        if (mod.isFabric) optional("modmenu")
        optional("enderman-overhaul")

        // Discord
        // Optionally set the announcement title used by the discord publisher
        // announcementTitle = "Download from CurseForge"
        // When using the discord webhook you must also specify the project slug
        // This is due to limitations in the CurseForge API.
        // projectSlug = "test-mod"
    }
}
