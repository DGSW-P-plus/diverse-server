dependencies {
    api(project(":common"))
    api(project(":business"))

    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    api("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
}
