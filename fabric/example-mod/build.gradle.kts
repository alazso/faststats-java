plugins {
    id("net.fabricmc.fabric-loom")
}

dependencies {
    implementation(project(":fabric"))
    minecraft("com.mojang:minecraft:26.1.2")
    implementation("net.fabricmc.fabric-api:fabric-api:0.152.1+26.2")
    compileOnly("net.fabricmc:fabric-loader:0.19.3")
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(project(":config").sourceSets["main"].output)
    from(project(":core").sourceSets["main"].output)
    from(project(":fabric").sourceSets["main"].output)
}