FROM mysql:8.0

# 设置字符集
ENV LANG=C.UTF-8

# 复制初始化脚本
COPY init.sql /docker-entrypoint-initdb.d/

# 暴露端口
EXPOSE 3306

CMD ["mysqld"]

# 健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD mysqladmin ping -h localhost -u root -p$MYSQL_ROOT_PASSWORD