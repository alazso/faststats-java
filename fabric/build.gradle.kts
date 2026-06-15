val moduleName by extra("dev.faststats.fabric")

plugins {
    id("net.fabricmc.fabric-loom") version ("1.15-SNAPSHOT")
}

dependencies {
    api(project(":core"))
    implementation(project(":config"))
    minecraft("com.mojang:minecraft:26.1.2")
    compileOnly("net.fabricmc.fabric-api:fabric-api:0.152.1+26.2")
    compileOnly("net.fabricmc:fabric-loader:0.19.3")
}
