tesks {
    runIde {
        // 启用热重载功能，使用Build菜单编译项目后无需重启调试进程即可完成，仅支持JBR
        jvmArgs += listOf("-XX:+AllowEnhancedClassRedefinition")
    }
}
