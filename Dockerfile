FROM eclipse-temurin:17
VOLUME /tmp
COPY build/libs/git-0.0.1-SNAPSHOT.jar git-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/git-0.0.1-SNAPSHOT.jar"]