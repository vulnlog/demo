val releaseBranch0 by ReleaseBranchProvider
val releaseBranch1 by ReleaseBranchProvider
val releaseBranch2 by ReleaseBranchProvider

val owaspDependencyCheck by ReporterProvider
val snykOpenSource by ReporterProvider

vuln("SNYK-JAVA-ORGJETBRAINSKOTLIN-2393744", "CVE-2020-29582") {
    report from snykOpenSource at "2025-07-02" on releaseBranch2..releaseBranch2
    analysis verdict low because """
        Information exposure vulnerability in Kotlin Stdlib. The vulnerable code is not used.
    """.trimIndent()
    task update "org.jetbrains.kotlin:kotlin-stdlib" atLeastTo "2.1.0" on releaseBranch2
    execution suppress untilNextPublication on releaseBranch2
}

vuln("SNYK-JAVA-COMMONSCOLLECTIONS-30078", "CVE-2015-7501") {
    report from snykOpenSource at "2025-07-02" on releaseBranch2..releaseBranch2
    analysis verdict notAffected because """
        Deserialization of untrusted data vulnerability in Apache Commons. The vulnerable code is not used.
    """.trimIndent()
    task update "commons-beanutils:commons-beanutils" atLeastTo "1.11.0" on releaseBranch2
    execution suppress untilNextPublication on releaseBranch2
}

vuln("CVE-2025-48734", "SNYK-JAVA-COMMONSBEANUTILS-10259368") {
    report from setOf(owaspDependencyCheck, snykOpenSource) at "2025-06-29" on releaseBranch2..releaseBranch2
    analysis verdict notAffected because """
        Improper Access Control vulnerability in Apache Commons. The vulnerable code is not used.
    """.trimIndent()
    task update "commons-beanutils:commons-beanutils" atLeastTo "1.11.0" on releaseBranch2
    execution suppress untilNextPublication on releaseBranch2
}

vuln("CVE-2014-0114", "SNYK-JAVA-COMMONSBEANUTILS-30077", "CVE-2019-10086") {
    report from setOf(owaspDependencyCheck, snykOpenSource) at "2025-06-29" on releaseBranch2..releaseBranch2
    analysis verdict notAffected because """
        Arbitrary code execution via the class parameter via classloader. The vulnerable code is not used.
    """.trimIndent()
    task update "commons-beanutils:commons-beanutils" atLeastTo "1.11.0" on releaseBranch2
    execution suppress untilNextPublication on releaseBranch2
}

vuln("CVE-2015-6420", "SNYK-JAVA-COMMONSCOLLECTIONS-472711") {
    report from setOf(owaspDependencyCheck, snykOpenSource) at "2025-06-29" on releaseBranch2..releaseBranch2
    analysis verdict notAffected because """
        Deserialization of Untrusted Data vulnerability. The vulnerable code is not used.
    """.trimIndent()
    task update "commons-beanutils:commons-beanutils" atLeastTo "1.11.0" on releaseBranch2
    execution suppress untilNextPublication on releaseBranch2
}


vuln("CVE-2015-4852", "SNYK-JAVA-COMMONSCOLLECTIONS-6056408") {
    report from setOf(owaspDependencyCheck, snykOpenSource) at "2025-06-29" on releaseBranch2..releaseBranch2
    analysis verdict notAffected because """
        Arbitrary Remote Code Execution due to Unsafe Deserialization. The vulnerable code is not used.
    """.trimIndent()
    task update "commons-beanutils:commons-beanutils" atLeastTo "1.11.0" on releaseBranch2
    execution suppress untilNextPublication on releaseBranch2
}

vuln("CVE-2025-005") {
    report from owaspDependencyCheck at "2025-04-08" on releaseBranch2..releaseBranch2
}

vuln("CVE-2025-004") {
    val timeToWait = 4.days
    report from owaspDependencyCheck at "2025-03-08" on releaseBranch1..releaseBranch2
    analysis analysedAt "2025-01-12" verdict critical because """
        |Vulnerability in core functionality. This needs to be patched as soon as possible.
        |Unfortunately there is no updated library available yet. Please check again in a
        |few days.""".trimMargin()
    task waitOnAllFor timeToWait
    execution suppress temporarily forTime timeToWait on all
}

vuln("CVE-2025-003") {
    report from owaspDependencyCheck at "2025-03-08" on releaseBranch1..releaseBranch2
    analysis analysedAt "2025-01-12" verdict high because """
        |A vulnerability in an important cryptographic method is being used by customers. Although 
        |the method is only used in very rare circumstances, the library should be patched in the 
        |next release.""".trimMargin()
    task update "yet.another:dependency" atLeastTo "8.3.25" on all
    execution suppress untilNextPublication on releaseBranch1 fixedAt "2025-03-08" on releaseBranch2
}

vuln("CVE-2025-002") {
    report from owaspDependencyCheck at "2025-02-08" on releaseBranch0..releaseBranch1
    analysis analysedAt "2025-01-12" verdict low because """
        |To exploit this vulnerability an attacker need access to the internal network. This is 
        |highly unlikely to happen and the risk therefore is considered to be low.""".trimMargin()
    task update "vulnerable.dependency" atLeastTo "2.3" on releaseBranch0
    execution suppress permanent on releaseBranch0 suppress untilNextPublication on releaseBranch1
}

vuln("CVE-2025-001") {
    report from owaspDependencyCheck at "2025-01-08" on releaseBranch0..releaseBranch0
    analysis analysedAt "2025-01-12" verdict notAffected because "The vulnerable method `foo()` is not used."
    task update "vulnerable.dependency" atLeastTo "1.2.3" on releaseBranch0
    execution suppress untilNextPublication on releaseBranch0
}
