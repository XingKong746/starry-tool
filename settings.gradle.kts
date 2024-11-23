buildscript {
    repositories {
        mavenLocal()
        maven { name = "tencentyun-maven"; url = uri("https://mirrors.tencent.com/maven") }
        maven { name = "aliyun-google-maven"; url = uri("https://maven.aliyun.com/repository/google") }
        maven { name = "aliyun-gradle-plugin"; url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "starry-tool"

include("starry-core")
include("starry-mail")
include("starry-mail-spring-boot-starter")
include("starry-web")
