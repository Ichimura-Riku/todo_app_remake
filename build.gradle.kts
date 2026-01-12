import org.gradle.kotlin.dsl.libs


// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.hilt.android) apply false
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {

        ratchetFrom("origin/main")
        kotlin {
            target("src/**/*.kt")
            ktlint(libs.versions.ktlint.get())
                .setEditorConfigPath("${rootProject.projectDir}/.editorconfig")
                .customRuleSets(
                    listOf(
                        libs.compose.rules.ktlint
                            .get()
                            .toString(),
                    ),
                )
        }
        format("misc") {
            target("*.md", ".gitignore", "*.xml", "*.yml")
            trimTrailingWhitespace()
            endWithNewline()
        }

        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
    afterEvaluate {
        tasks.named("preBuild") {
            dependsOn("spotlessApply")
        }
    }
}

buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }
}
