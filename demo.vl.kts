val releaseBranch0 by ReleaseBranchProvider
val releaseBranch1 by ReleaseBranchProvider
val releaseBranch2 by ReleaseBranchProvider

val dependencyScanner1 by ReporterProvider
val dependencyScanner2 by ReporterProvider

val bothScanner = setOf(dependencyScanner1, dependencyScanner2)

vuln("CVE-2025-005") {
    report from dependencyScanner1 at "2025-04-08" on releaseBranch2..releaseBranch2
}

vuln("CVE-2025-004") {
    val timeToWait = 4.days
    report from dependencyScanner1 at "2025-03-08" on releaseBranch1..releaseBranch2
    analysis analysedAt "2025-01-12" verdict critical because """
        |Vulnerability in core functionality. This needs to be patched as soon as possible.
        |Unfortunately there is no updated library available yet. Please check again in a
        |few days.""".trimMargin()
    task waitOnAllFor timeToWait
    execution suppress temporarily forTime timeToWait on all
}

vuln("CVE-2025-003") {
    report from bothScanner at "2025-03-08" on releaseBranch1..releaseBranch2
    analysis analysedAt "2025-01-12" verdict high because """
        |A vulnerability in an important cryptographic method is being used by customers. Although 
        |the method is only used in very rare circumstances, the library should be patched in the 
        |next release.""".trimMargin()
    task update "yet.another:dependency" atLeastTo "8.3.25" on all
    execution suppress untilNextPublication on releaseBranch1 fixedAt "2025-03-08" on releaseBranch2
}

vuln("CVE-2025-002") {
    report from dependencyScanner1 at "2025-02-08" on releaseBranch0..releaseBranch1
    analysis analysedAt "2025-01-12" verdict low because """
        |To exploit this vulnerability an attacker need access to the internal network. This is 
        |highly unlikely to happen and the risk therefore is considered to be low.""".trimMargin()
    task update "vulnerable.dependency" atLeastTo "2.3" on releaseBranch0
    execution suppress permanent on releaseBranch0 suppress untilNextPublication on releaseBranch1
}

vuln("CVE-2025-001") {
    report from dependencyScanner1 at "2025-01-08" on releaseBranch0..releaseBranch0
    analysis analysedAt "2025-01-12" verdict notAffected because "The vulnerable method `foo()` is not used."
    task update "vulnerable.dependency" atLeastTo "1.2.3" on releaseBranch0
    execution suppress untilNextPublication on releaseBranch0
}
