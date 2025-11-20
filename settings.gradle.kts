rootProject.name = "sample-project"
include("data")
include("web")
include("panel")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("reflect", "org.jetbrains.kotlin", "kotlin-reflect").withoutVersion()
            library("stdlib-jdk8", "org.jetbrains.kotlin", "kotlin-stdlib-jdk8").withoutVersion()
            bundle("kotlin", listOf("reflect", "stdlib-jdk8"))

            library("kotlin-reactor", "io.projectreactor.kotlin", "reactor-kotlin-extensions").withoutVersion()
            library("kotlin-coroutines-reactor", "org.jetbrains.kotlinx", "kotlinx-coroutines-reactor").withoutVersion()
            library("kotlin-jackson", "com.fasterxml.jackson.module", "jackson-module-kotlin").withoutVersion()
            library("webflux", "org.springframework.boot", "spring-boot-starter-webflux").withoutVersion()
            bundle("kotlin-webflux", listOf("reflect", "stdlib-jdk8", "kotlin-reactor", "kotlin-coroutines-reactor", "kotlin-jackson", "webflux"))

            library("r2dbc", "org.springframework.boot", "spring-boot-starter-data-r2dbc").withoutVersion()
            library("r2dbc-postgres", "org.postgresql", "r2dbc-postgresql").withoutVersion()
            bundle("r2dbc-postgres", listOf("r2dbc", "r2dbc-postgres"))

            library("spring-cloud-bom", "org.springframework.cloud", "spring-cloud-dependencies").version { require("2023.0.3") }

            library("elemento-core", "org.jboss.elemento", "elemento-core").version { require("1.4.2") }
            library("elemental2-svg", "com.google.elemental2", "elemental2-svg").version { require("1.2.1") }
            library("gwt-user", "org.gwtproject", "gwt-user").version { require("2.11.0") }
            library("gwt-dev", "org.gwtproject", "gwt-dev").version { require("2.11.0") }
            bundle("gwt", listOf("elemento-core", "elemental2-svg", "gwt-user"))
            library("lombok", "org.projectlombok", "lombok").version { require("1.18.32") }
            library("jackson-annotations", "com.fasterxml.jackson.core", "jackson-annotations").version { require("2.17.2") }
        }
    }
}
