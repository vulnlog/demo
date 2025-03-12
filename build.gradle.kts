plugins {
    id("java")
    id("dev.vulnlog.dslplugin") version "0.5.3"
}

group = "dev.vulnlog.vulnlogdemoproject"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

vulnlog {
    definitionsFile.set(layout.projectDirectory.file("definitions.vl.kts"))
    reportOutput.set(layout.buildDirectory.dir("vulnlog-reports"))
}
