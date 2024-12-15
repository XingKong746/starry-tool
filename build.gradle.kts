plugins {
    id("java-library") // java，可使用api方式导包
    id("maven-publish") // maven发布插件
    id("signing") // 组件签名插件
}

// 范围条件：为特定的项目排除一些配置
val excludes = arrayOf("bom", "......")

allprojects {
    apply {
        plugin("maven-publish")// maven发布插件
        plugin("signing")// 组件签名插件
    }

    group = "cn.starrys"
    version = "0.1.0-SNAPSHOT"
    description = "星空工具"

    repositories {
        mavenLocal()
        maven { name = "tencentyun-maven"; url = uri("https://mirrors.tencent.com/maven") }
        maven { name = "aliyun-google-maven"; url = uri("https://maven.aliyun.com/repository/google") }
        maven { name = "aliyun-gradle-plugin"; url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        mavenCentral()
        gradlePluginPortal()
        google()
    }

    tasks.clean { delete("${projectDir}/out") }
}

subprojects {
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


    // 发布到Maven仓库方面的配置
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                pom {
                    // eg: name = "星空的工具包"（此 name 非artifactId）
                    name = project.name
                    name = "${project.group}:${project.name}"
                    // 描述，子项目最好单独配置一下此处配置，不然只会使用根项目中的描述
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
                    // 开发者信息
                    developers {
                        // 开发者
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
                    // 版本控制仓库
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
                name = "MavenCentral"
                val sonatypeRepository = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
                val sonatypeSnapshotRepository = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
                url = if (version.toString().endsWith("SNAPSHOT")) sonatypeSnapshotRepository else sonatypeRepository
                credentials {
                    username = properties["username"].toString()
                    password = properties["password"].toString()
                }
            }
        }
    }
    // 使用signing插件对jar包签名
    signing {
        sign(publishing.publications["mavenJava"])
    }
}

/**
 * 检查字符串是否不以指定的任一后缀结尾
 *
 * @param mainString 待检查的主字符串
 * @param endStrings 后缀数组，用于检查主字符串是否以这些后缀中的任一后缀结尾
 * @return 如果主字符串不以任何指定的后缀结尾，则返回true；否则返回false
 */
fun notEndsWith(mainString: String, endStrings: Array<String>): Boolean {
    // 初始化状态为true，表示主字符串不以后缀数组中的任一后缀结尾
    var status = true
    // 遍历后缀数组
    for (endStr in endStrings) {
        // 如果主字符串以后缀数组中的某个后缀结尾，将状态设置为false，并终止遍历
        if (mainString.endsWith(endStr)) {
            status = false
            break
        }
    }
    // 返回最终状态
    return status
}
