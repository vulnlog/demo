# Vulnlog Demo Project

This is a Vulnlog demo project to demonstrate the use of Vulnlog.

Show the Vulnlog CLI version:

```shell
./gradlew showCliVersion
```

Generate a report for the demo project:

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

For more information about the project, check out [Vulnlog](https://github.com/vulnlog/vulnlog).
