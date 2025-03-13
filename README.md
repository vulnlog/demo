# Vulnlog Demo Project

This is a Demo Project to show how to get started using Vulnlog.

In your Gradle project, simply add the Vulnlog Gradle plugin to your build file:

```kotlin
plugins {
    id("java")
    id("dev.vulnlog.dslplugin") version "$version"
}
```

Run the `showCliVersion` task to show the Vulnlog CLI version:

```shell
./gradlew showCliVersion
```

To generate reports, configure the `vulnlog` in your `build.gradle.kts` file:

```kotlin
vulnlog {
    definitionsFile.set(layout.projectDirectory.file("definitions.vl.kts"))
    reportOutput.set(layout.buildDirectory.dir("vulnlog-reports"))
}
```

Run the `generateReport` task to create the reports for the demo project:

```shell
./gradlew generateReport
```

Depending on the configuration of the plugin in the `build.gradle.kts` file, the report will be
generated in `build/vulnlog-reports`:

```terminal
ls -1 build/vulnlog-reports/
report-v1.html
report-v2.html
```

More information and documentation about the project can be found
at [Vulnlog](https://github.com/vulnlog/vulnlog).
