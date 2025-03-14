releases {
    branch("branch 1") {
        release("0.1.0", "2025-01-01")
        release("0.1.1", "2025-01-23")
        release("0.2.0")
    }
    branch("branch 2") {
        release("2.0.0", "2025-02-01")
        release("2.1.0")
    }
}

reporters {
    reporter("demo reporter")
}
