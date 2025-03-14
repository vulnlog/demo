val branch1 by ReleaseBranchProvider
val branch2 by ReleaseBranchProvider

val demoReporter by ReporterProvider

vuln("CVE-1337-42") {
    report from demoReporter at "2025-01-28" on branch1..branch2
    analysis analysedAt "2025-01-30" verdict notAffected because """
        This is just a demo entry for demonstration purpose.
    """.trimIndent()
    task update "vulnerable.dependency" atLeastTo "1.2.3" on all
    execution suppress untilNextPublication on all
}
