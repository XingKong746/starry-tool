plugins {
    id("java-library")// java，可使用api方式导包
    id("maven-publish")// maven发布插件
    id("signing")// 组件签名插件
}

allprojects {
    apply {
        plugin("maven-publish")// maven发布插件
        plugin("signing")// 组件签名插件
    }

    group = "cn.starrys"
    version = "0.0.1-SNAPSHOT"
    version = "0.0.1"
    description = "星空工具"

    repositories {
        mavenLocal()
        maven { name = "tencentyun-maven"; url = uri("https://mirrors.tencent.com/maven") }
        maven { name = "aliyun-google-maven"; url = uri("https://maven.aliyun.com/repository/google") }
        maven { name = "aliyun-gradle-plugin"; url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        gradlePluginPortal()
        mavenCentral()
    }

    tasks.clean { delete("$projectDir/out") }
}

subprojects {
    // 范围条件：为特定的项目排除一些配置
    val scope: Boolean = !project.name.endsWith("bom")

    if (scope) {
        apply {
            plugin("java-library")// java，可使用api方式导包
        }

        tasks.compileJava.configure { options.encoding = "UTF-8" }
        tasks.compileTestJava.configure { options.encoding = "UTF-8" }
        tasks.javadoc.configure {
            options {
                encoding = "UTF-8"
                version = true
            }
        }
        tasks.test { useJUnitPlatform() }

        java {
            // 生成 source.jar
            withSourcesJar()
            // 生成 javadoc.jar
            withJavadocJar()
        }
    }

    // 发布到Maven仓库方面的配置
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                if (scope) {
                    from(components["java"])
                }
                pom {
                    // eg: name = "星空的工具包"（此 name 非artifactId）
                    name = project.name
                    // 描述
                    description = project.description
                    // 项目地址
                    url = "https://gitee.com/XingKong746/starry-tool"

                    licenses {
                        // 许可证名称和地址
                        license {
                            name = "MIT License"
                            url = "https://gitee.com/XingKong746/starry-tool/blob/main/LICENSE"
                        }
                    }
                    developers {
                        // 开发者信息
                        developer {
                            // 开发者ID
                            id = "11520532" // Gitee
                            // 开发者名
                            name = "XingKong746"
                            // 开发者邮箱
                            email = "xkhi@qq.com"
                        }
                        developer {
                            id = "79194954" // GitHub
                            // 开发者名
                            name = "XingKong746"
                            // 开发者邮箱
                            email = "q@xkhi.cn"
                        }
                    }
                    // 版本控制仓库地址
                    scm {
                        connection = "scm:git:git://gitee.com/XingKong746/starry-tool.git"
                        developerConnection = "scm:git:ssh://gitee.com/XingKong746/starry-tool.git"
                        // 仓库url
                        url = "https://gitee.com/XingKong746/starry-tool.git"
                    }
                }
            }
        }
        repositories {
            maven {
                name = "OSSRH"
                // val sonatypeRepository = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
                val sonatypeRepository = uri("https://s01.oss.sonatype.org/content/repositories/releases/")
                // val sonatypeSnapshotRepository = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
                val sonatypeSnapshotRepository = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) sonatypeSnapshotRepository else sonatypeRepository
                credentials {
                    username = properties["ossrhUsername"].toString()
                    password = properties["ossrhPassword"].toString()
                }
            }
        }
        // 使用signing插件对jar包签名，否则无法上传到maven仓库
        signing {
            sign(publishing.publications["mavenJava"])
        }
    }
}
