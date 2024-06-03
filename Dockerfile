# 베이스 이미지 선택
FROM openjdk:17-jdk-slim

LABEL authors="seosang-won"

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일 복사
COPY build/libs/*.jar /app/myapp.jar

# dev 프로파일 활성화
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app/myapp.jar"]
