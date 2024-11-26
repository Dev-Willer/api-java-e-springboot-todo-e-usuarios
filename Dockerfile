FROM openjdk:17-jdk-slim AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

EXPOSE 8080

# Define o diretório de trabalho
WORKDIR /app

# Copia o código fonte para o contêiner
COPY . .

# Executa o comando Maven para construir o projeto
RUN mvn clean install