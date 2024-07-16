dependencies {
    api(project(":core"))

    api("org.springframework.boot:spring-boot-starter-websocket")
    api("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    api("io.jsonwebtoken:jjwt-api")
    runtimeOnly("io.jsonwebtoken:jjwt-impl")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson")
}
