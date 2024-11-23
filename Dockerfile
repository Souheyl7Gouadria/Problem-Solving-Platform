FROM bitnami/java:21 as build

RUN apt-get update -y && apt-get install maven -y

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM bitnami/java:21

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]

