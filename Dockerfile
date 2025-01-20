# Etapa 1: Construção
FROM maven:3.9.1-eclipse-temurin-17 AS build

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo pom.xml e as dependências do projeto para o contêiner
COPY pom.xml .

# Baixar as dependências do Maven (primeira execução para melhorar a cache)
RUN mvn dependency:go-offline

# Copiar o código fonte para o contêiner
COPY src /app/src

# Compilar e empacotar o projeto (sem rodar os testes)
RUN mvn clean package -DskipTests

# Etapa 2: Criação da imagem final
FROM eclipse-temurin:17-jdk AS runtime

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o JAR gerado na etapa de build
COPY --from=build /app/target/maquina_de_turing-0.0.1-SNAPSHOT.jar /app/app.jar

# Expor a porta 8080 para acessar o aplicativo
EXPOSE 8080

# Comando para rodar o JAR com Java
ENTRYPOINT ["java", "-jar", "app.jar"]
