dependencies {
    api(project(":core"))

    api("org.springframework.boot:spring-boot-starter-websocket")
    api("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    implementation("com.querydsl:querydsl-core")
    implementation(group = "com.querydsl", name = "querydsl-jpa", classifier = "jakarta")
    kapt("com.querydsl:querydsl-apt")

    api("io.jsonwebtoken:jjwt-api")
    runtimeOnly("io.jsonwebtoken:jjwt-impl")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson")
}
