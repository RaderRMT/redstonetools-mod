plugins {
    id("fabric-loom") version "1.0-SNAPSHOT" apply false
    id("com.replaymod.preprocess") version "48e02ad"
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://libraries.minecraft.net/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
        maven("https://github.com/jitsi/jitsi-maven-repository/raw/master/releases/")
        maven("https://maven.fabricmc.net/")
        maven("https://jitpack.io")

        maven {
            url = uri("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
            content {
                includeGroup("me.djtheredstoner")
            }
        }
    }
}

preprocess {
    val mc11903 = createNode("1.19.3", 11903, "yarn")
    val mc11902 = createNode("1.19.2", 11902, "yarn")
    val mc11901 = createNode("1.19.1", 11901, "yarn")
    val mc11900 = createNode("1.19", 11900, "yarn")
    val mc11802 = createNode("1.18.2", 11802, "yarn")

    mc11903.link(mc11902)
    mc11902.link(mc11901)
    mc11901.link(mc11900)
    mc11900.link(mc11802)
}
