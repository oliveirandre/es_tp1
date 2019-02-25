sudo ./mvnw install dockerfile:build
sudo docker run -p 8080:8080 springio/gs-spring-boot-docker
