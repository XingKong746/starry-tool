buildscript {
    repositories {
        maven { name = "aliyun-gradle-plugin"; url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        gradlePluginPortal()
    }
}

rootProject.name = "starry-tool"

include("starry-core")
include("starry-mail","starry-mail-spring-boot-starter")
