val v1 by ReleaseBranch
val v2 by ReleaseBranch

vuln("CVE-1337-42") {
    report from SCA_SCANNER at "2025-01-28" on v1..v2
    analysis analysedAt "2025-01-30" verdict "not affected" because """
        This is just a demo entry for demonstration purpose.
    """.trimIndent()
    task update "vulnerable.dependency" atLeastTo "1.2.3" on all
    execution suppress untilNextPublication on all
}
