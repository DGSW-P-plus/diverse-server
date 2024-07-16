dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb")
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    api("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    api(project(":business"))
    api(project(":common"))
}
