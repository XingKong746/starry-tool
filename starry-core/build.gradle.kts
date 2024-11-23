dependencies {
    api(rootProject.project.libs.fastjson2)

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
}
