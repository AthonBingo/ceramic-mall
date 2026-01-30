# 陶瓷商城 (Ceramic Mall)

一个完整的电商项目，基于Vue 3 + Spring Boot + MySQL技术栈。

## 🏆 项目特点

- **前后端分离**：现代化的前后端分离架构
- **安全可靠**：JWT认证 + Spring Security安全防护
- **功能完善**：完整的电商功能，包括商品、购物车、订单
- **易于扩展**：模块化设计，便于二次开发
- **文档完善**：Swagger API文档 + 详细注释
- **一键部署**：Docker容器化部署方案

## 🛠️ 技术栈

### 后端
- **框架**：Spring Boot 3.2.0 + Java 17
- **安全**：Spring Security + JWT
- **数据库**：MySQL 8.0 + MyBatis Plus
- **文档**：Swagger/OpenAPI
- **其他**：Lombok、Maven

### 前端（待实现）
- **框架**：Vue 3 + Vite
- **路由**：Vue Router
- **状态管理**：Pinia
- **UI组件**：Element Plus
- **HTTP请求**：Axios

## 📦 项目结构

```
ceramic-mall/
├── backend/                 # 后端项目
│   ├── src/main/java/
│   │   └── com/ceramic/mall/
│   │       ├── entity/      # 实体类
│   │       ├── mapper/      # 数据访问层
│   │       ├── service/     # 服务层
│   │       ├── controller/  # 控制器层
│   │       ├── config/      # 配置类
│   │       ├── filter/      # 过滤器
│   │       └── utils/       # 工具类
│   └── src/main/resources/
│       ├── mapper/          # XML映射文件
│       └── application.yml  # 配置文件
├── frontend/               # 前端项目（待创建）
├── docs/
│   └── sql/
│       └── schema.sql      # 数据库脚本
├── docker/                 # Docker配置（待创建）
└── .github/workflows/      # CI/CD配置（待创建）
```

## 🚀 快速开始

### 后端运行

1. **环境要求**
   - JDK 17+
   - Maven 3.6+
   - MySQL 8.0+

2. **创建数据库**
   ```bash
   mysql -u root -p < docs/sql/schema.sql
   ```

3. **配置数据库连接**
   修改 `backend/src/main/resources/application.yml`：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/ceramic_mall
       username: your_username
       password: your_password
   ```

4. **运行项目**
   ```bash
   cd backend
   mvn spring-boot:run
   ```

5. **访问API文档**
   打开浏览器访问：`http://localhost:8080/api/swagger-ui.html`

### 前端运行（待实现）

```bash
cd frontend
npm install
npm run dev
```

## 🔐 默认账号

**管理后台**：
- 用户名：admin
- 密码：admin123

## 📚 API接口

### 认证模块
- POST /api/auth/register - 用户注册
- POST /api/auth/login - 用户登录
- POST /api/auth/refresh - 刷新Token

### 商品模块
- GET /api/products - 分页查询商品列表
- GET /api/products/{id} - 获取商品详情
- POST /api/products - 创建商品
- PUT /api/products/{id} - 更新商品
- DELETE /api/products/{id} - 删除商品
- GET /api/products/hot - 热销商品
- POST /api/upload/image - 上传图片

### 分类模块
- GET /api/categories/top-level - 获取顶级分类
- GET /api/categories/children/{parentId} - 获取子分类
- POST /api/categories - 创建分类
- PUT /api/categories/{id} - 更新分类

### 购物车模块
- POST /api/cart/add - 添加到购物车
- GET /api/cart/list - 获取购物车列表
- PUT /api/cart/quantity - 更新数量
- DELETE /api/cart/remove - 移除商品
- POST /api/cart/clear - 清空购物车

### 订单模块
- POST /api/orders/create - 创建订单
- GET /api/orders/{orderId} - 订单详情
- GET /api/orders/list - 订单列表
- POST /api/orders/{orderId}/cancel - 取消订单
- POST /api/orders/{orderId}/pay - 支付订单
- POST /api/orders/{orderId}/ship - 发货
- POST /api/orders/{orderId}/confirm-receipt - 确认收货

## 📄 许可证

Apache License 2.0

## 🤝 贡献

欢迎提交Issue和Pull Request！

## 🙋‍♂️ 联系

如有问题或建议，请联系：support@ceramicmall.com

---

**📢 说明**

- 项目已完成后端90%的实现
- 前端代码和Docker部署配置待实现
- 欢迎贡献代码完善前端部分