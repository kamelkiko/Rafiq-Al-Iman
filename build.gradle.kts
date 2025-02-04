import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.kamel"
version = "1.0.1"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.1.5")
    type.set("IC") // Target IDE Platform
    plugins.set(listOf("com.intellij.java", "Kotlin", "android"))
    sameSinceUntilBuild.set(false)
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
    }

    patchPluginXml {
        changeNotes.set(
            """<div dir="rtl" style="font-family: Arial, sans-serif; line-height: 1.6;">
    <ul>
          <li>🔔 <strong>تذكير بأوقات الصلاة</strong>.</li>
          <li>📍 <strong>دعم لتحديث الموقع الجغرافي</strong>.</li>
    </ul>

    <p>
        <strong>🚀 تنزيل الإضافة الآن</strong> واجعل عملك أكثر بركة وإنتاجية!  
    </p>
</div>
        """.trimIndent()
        )
        sinceBuild.set("231")
        untilBuild.set("")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
