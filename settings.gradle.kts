buildscript {
    repositories {
        mavenLocal()
        maven { name = "aliyun-gradle-plugin"; url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { name = "tencentyun-maven"; url = uri("https://mirrors.tencent.com/maven") }
        maven { name = "aliyun-google-maven"; url = uri("https://maven.aliyun.com/repository/google") }
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

rootProject.name = "starry-tool"

include("starry-core")
include("starry-mail","starry-mail-spring-boot-starter")
