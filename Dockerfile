# jar 파일을 실행할 컨테이너를 실행할 dockerfile?
# 기존: FROM eclipse-temurin:17-jre
FROM eclipse-temurin:17-jre

# 1) 타임존 데이터 설치 (Debian/Ubuntu 베이스)
RUN apt-get update && apt-get install -y --no-install-recommends tzdata \
    && rm -rf /var/lib/apt/lists/*

# 2) 기본 TZ + JDBC 타임존 옵션 (ORA-01882 예방)
ENV TZ=Asia/Seoul
ENV JAVA_TOOL_OPTIONS="-Duser.timezone=Asia/Seoul -Doracle.jdbc.timezoneAsRegion=false"

# 이 jar 파일은 깃허브에서 직접 만들 예정
WORKDIR /
ARG JAR_FILE=target/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 9000 번 포트에서 jar 파일을 실행해라
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app.jar"]
