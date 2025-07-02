releases {
    branch("Release Branch 0") {
        release("0.1.0", "2025-01-01")
        release("0.1.1", "2025-01-23")
        release("0.2.0")
    }
    branch("Release Branch 1") {
        release("1.0.0", "2025-02-01")
        release("1.1.0", "2025-02-23")
        release("1.1.1", "2025-03-06")
        release("1.1.2")
    }
    branch("Release Branch 2") {
        release("2.0.0", "2025-04-01")
        release("2.1.0")
    }
}

reporters {
    reporter("OWASP Dependency Check") {
        suppression {
            templateFilename = "owasp-dependency-check.xml"
            idMatcher = "CVE-.*"
            template = """
                |<suppress {{ until="vulnlogEnd" }}>
                |    <notes><![CDATA[
                |        {{ vulnlogReasoning }}
                |    ]]></notes>
                |    <vulnerabilityName>{{ vulnlogId }}</vulnerabilityName>
                |</suppress>
                |""".trimMargin()
        }
    }
    reporter("Snyk Open Source") {
        suppression {
            templateFilename = "snyk.yml"
            idMatcher = "SNYK-.*"
            template = """
                |{{ vulnlogId }}:
                |  - '*':
                |      reason: >-
                |        {{ vulnlogReasoning }}
                |      created: {{ vulnlogStart }}
                |      {{ expires: vulnlogEnd }}""".trimMargin()
        }
    }
}
