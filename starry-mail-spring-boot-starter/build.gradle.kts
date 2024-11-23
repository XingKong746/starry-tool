group = "cn.starrys"
version = "3.1.1"
description = "邮件工具，spring boot starter"

dependencies {
    // Lombok
    compileOnly(rootProject.libs.lombok)
    annotationProcessor(rootProject.libs.lombok)
    // 日志框架
    implementation(rootProject.libs.logback)
    // JetBrains注解
    implementation(rootProject.libs.jetbrains.annotation)


    // Junit5
    testImplementation(platform(rootProject.libs.junit.bom))
    testImplementation("org.junit.jupiter:junit-jupiter")

    api(project(":starry-mail"))
    implementation(libs.spring.boot.autoconfigure)
    annotationProcessor(libs.spring.boot.configuration.processor)
}

publishing {
    publications {
        getByName<MavenPublication>("mavenJava") {
            pom {
                // eg: name = "星空的工具包"（此 name 非artifactId）
                name = project.name
                // 描述
                description = project.description
            }
        }
    }
}
