dependencies {
    api(project(":core"))

    api("org.springframework.boot:spring-boot-starter-amqp")
    api("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    implementation("com.querydsl","querydsl-jpa", classifier = "jakarta")

    api("io.jsonwebtoken:jjwt-api")
    runtimeOnly("io.jsonwebtoken:jjwt-impl")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson")
}
