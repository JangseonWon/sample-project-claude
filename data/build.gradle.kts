plugins {
    kotlin("jvm")
    id("java")
    id("maven-publish")
}

dependencies {
    implementation(libs.lombok)
    annotationProcessor(libs.lombok)
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.2")
}
tasks {
    withType<Delete> { doFirst { delete("build/") } }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<Jar> {
        from(sourceSets.main.get().allSource)
    }
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.gcgenome"
            artifactId = "sample-data"
            version = "2025.08.25-2"
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
tasks.test {
    useJUnitPlatform()
}
