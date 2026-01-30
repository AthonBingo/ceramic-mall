# 使用OpenJDK 17作为基础镜像
FROM openjdk:17-jdk-slim

# 安装必要的工具
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# 设置工作目录
WORKDIR /app

# 复制Maven构建文件
COPY pom.xml .

# 复制源代码
COPY src ./src

# 下载依赖并构建应用
RUN apt-get update && apt-get install -y maven && \
    mvn dependency:go-offline && \
    mvn clean package -DskipTests && \
    apt-get remove -y maven && \
    rm -rf /root/.m2

# 暴露端口
EXPOSE 8080

# 创建上传目录
RUN mkdir -p /app/uploads && chmod 777 /app/uploads

# 运行应用
CMD ["java", "-jar", "target/ceramic-mall-1.0.0.jar"]

# 健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/api/actuator/health || exit 1