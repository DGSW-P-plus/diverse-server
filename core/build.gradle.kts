dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-amqp")

    api("com.querydsl:querydsl-core")
    kapt("com.querydsl", "querydsl-apt", classifier = "jakarta")

    api(project(":business"))
    api(project(":common"))
}
