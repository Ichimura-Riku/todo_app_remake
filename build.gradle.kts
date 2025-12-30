import org.gradle.kotlin.dsl.libs


// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.spotless)
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {

        ratchetFrom("origin/main")
        kotlin {
            target("src/**/*.kt")
            ktlint("1.5.0")
                .setEditorConfigPath("$projectDir/.editorconfig") // sample unusual placement
                .editorConfigOverride(
                    mapOf(
                        "indent_size" to 4,
                        // intellij_idea is the default style we preset in Spotless, you can override it referring to https://pinterest.github.io/ktlint/latest/rules/code-styles.
                        "ktlint_code_style" to "intellij_idea",
                    ),
                ).customRuleSets(
                    listOf(
                        "io.nlopez.compose.rules:ktlint:0.4.16",
                    ),
                )
            endWithNewline()
        }
        format("misc") {
            target("*.md", ".gitignore", "*.xml", "*.gradle")
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
