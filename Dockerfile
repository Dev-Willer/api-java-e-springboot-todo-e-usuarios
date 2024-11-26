FROM ubuntu:latest AS build

# Atualiza o sistema e instala dependências
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho como /app
WORKDIR /app

# Copia o código fonte para o contêiner
COPY . .

# Executa o Maven para construir o projeto
RUN mvn clean install

# A segunda parte da imagem com OpenJDK Slim para execução
FROM openjdk:17-jdk-slim

# Expõe a porta 8080
EXPOSE 8080

# Copia o arquivo JAR da etapa anterior para a etapa de execução
COPY --from=build /app/target/todolist-1.0.0.jar app.jar

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]