
FROM maven:3.8.3-openjdk-17

WORKDIR /app

COPY  pom.xml ./
COPY src ./src
RUN mvn dependency:resolve


CMD ["mvn", "spring-boot:run"]

