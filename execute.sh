./mvnw install dockerfile:build
docker run -p 8080:8080 springio/gs-spring-boot-docker
