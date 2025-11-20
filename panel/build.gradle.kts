plugins {
    kotlin("jvm")
    id("org.wisepersist.gwt") version "1.1.19"
    id("java")
    id("maven-publish")
}

group = "com.gcgenome"
version = "1.0"

dependencies {
    implementation(libs.bundles.gwt)
    compileOnly(libs.gwt.dev)
    implementation("com.gcgenome:gateway-api:1.0")
    implementation("net.sayaya:ui:4.1")
    implementation(libs.lombok)
    annotationProcessor(libs.lombok)
}
tasks {
    withType<Delete> { doFirst { delete("build/") } }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<Jar> {
        from(sourceSets.main.get().allSource)
        archiveFileName.set("sample-service-sample-panel")
    }
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.gcgenome"
            artifactId = "sample-panel"
            version = "1.0"
            from(components["java"])
        }
    }
    repositories {
        publications {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/GC-Genome/maven")
                credentials {
                    username = project.findProperty("github_username") as String? ?: System.getenv("GITHUB_USERNAME")
                    password = project.findProperty("github_password") as String? ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}