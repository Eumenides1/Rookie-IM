# 1. 使用 Java 21 的基础镜像
FROM eclipse-temurin:21-jdk

# 2. 创建工作目录
WORKDIR /app

# 3. 将当前目录下的所有内容复制到容器中的 /app 目录
COPY target/your-service.jar /app/im-gateway-1.0-SNAPSHOT.jar

# 4. 暴露服务的端口（根据每个微服务的端口不同配置）
EXPOSE 8080

# 5. 设置容器启动时执行的命令，启动 Java 服务
ENTRYPOINT ["java", "-jar", "/app/im-gateway-1.0-SNAPSHOT.jar"]
