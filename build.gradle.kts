plugins {
    id("java")
    id("dev.vulnlog.dslplugin") version "0.9.0"
    id("org.owasp.dependencycheck") version "12.1.3"
}

group = "dev.vulnlog.vulnlogdemoproject"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // dependency added for demonstration purpose only
    implementation("commons-beanutils:commons-beanutils:1.11.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

vulnlog {
    definitionsFile.set(layout.projectDirectory.file("definitions.vl.kts"))
    reportOutput.set(layout.buildDirectory.dir("vulnlog-reports"))

    suppressionTemplates.set(layout.projectDirectory.dir("vulnlog/templates"))
    suppressionOutput.set(layout.buildDirectory.dir("vulnlog-suppressions"))
}

dependencyCheck {
    val suppressionFileProvider: Provider<RegularFile> =
        layout.buildDirectory.file("vulnlog-suppressions/owasp-dependency-check-release-branch-2.xml")
    suppressionFile = suppressionFileProvider.get().asFile.absolutePath

    // fail the build if a CVSS is equal of above 5.0
    failBuildOnCVSS = 5.0f

    nvd {
        apiKey = System.getenv("OWASP_DEPENDENCY_CHECK_API_KEY")
    }
}
