-- =============================================
-- 陶瓷商城数据库表结构
-- =============================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS ceramic_mall DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE ceramic_mall;

-- 用户表
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '手机号',
    avatar VARCHAR(255) COMMENT '头像URL',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除标记，0未删除，1已删除',
    INDEX idx_username (username),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 商品分类表
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT '分类名称',
    parent_id BIGINT DEFAULT 0 COMMENT '父分类ID，0表示顶级分类',
    sort_order INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除标记，0未删除，1已删除',
    INDEX idx_parent_id (parent_id),
    INDEX idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- 商品表
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL COMMENT '商品名称',
    description TEXT COMMENT '商品描述',
    price DECIMAL(10, 2) NOT NULL COMMENT '价格',
    stock INT NOT NULL DEFAULT 0 COMMENT '库存',
    category_id BIGINT COMMENT '分类ID',
    images TEXT COMMENT '图片URL数组，使用逗号分隔',
    sales INT DEFAULT 0 COMMENT '销量',
    status TINYINT DEFAULT 1 COMMENT '状态：0下架，1上架',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除标记，0未删除，1已删除',
    INDEX idx_category_id (category_id),
    INDEX idx_status (status),
    INDEX idx_sales (sales),
    INDEX idx_create_time (create_time),
    FOREIGN KEY (category_id) REFERENCES categories(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 购物车表
CREATE TABLE IF NOT EXISTS carts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL COMMENT '用户ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    quantity INT NOT NULL COMMENT '数量',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除标记，0未删除，1已删除',
    INDEX idx_user_id (user_id),
    INDEX idx_product_id (product_id),
    UNIQUE KEY uk_user_product (user_id, product_id, deleted),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- 订单表
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    total_amount DECIMAL(10, 2) NOT NULL COMMENT '订单总额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0待支付，1已支付，2已发货，3已完成，4已退款，5已取消',
    address VARCHAR(500) NOT NULL COMMENT '收货地址',
    phone VARCHAR(20) NOT NULL COMMENT '收货电话',
    receiver VARCHAR(50) NOT NULL COMMENT '收货人',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    complete_time DATETIME COMMENT '完成时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除标记，0未删除，1已删除',
    INDEX idx_order_no (order_no),
    INDEX idx_user_id (user_id),
    INDEX idx_status (status),
    INDEX idx_create_time (create_time),
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单详情表
CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    product_name VARCHAR(200) NOT NULL COMMENT '商品名称',
    product_price DECIMAL(10, 2) NOT NULL COMMENT '商品价格',
    quantity INT NOT NULL COMMENT '数量',
    total_price DECIMAL(10, 2) NOT NULL COMMENT '总价',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除标记，0未删除，1已删除',
    INDEX idx_order_id (order_id),
    INDEX idx_product_id (product_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- =============================================
-- 插入初始数据
-- =============================================

-- 初始化商品分类
INSERT INTO categories (name, parent_id, sort_order) VALUES
('茶具', 0, 1),
('餐具', 0, 2),
('花瓶', 0, 3),
('摆件', 0, 4),
('茶具套装', 1, 1),
('茶杯', 1, 2),
('茶盘', 1, 3),
('砂锅', 2, 1),
('碗碟', 2, 2),
('创意花瓶', 3, 1),
('装饰摆件', 4, 1);

-- 初始化商品数据
INSERT INTO products (name, description, price, stock, category_id, images, sales, status) VALUES
('景德镇陶瓷茶具套装', '精美景德镇陶瓷茶具套装，包含茶壶、茶杯、茶盘', 588.00, 100, 5, 'tea_set_1.jpg,tea_set_2.jpg,tea_set_3.jpg', 156, 1),
('宜兴紫砂茶壶', '正宗宜兴紫砂壶，手工制作，透气性好', 298.00, 50, 1, 'purple_clay_pot_1.jpg,purple_clay_pot_2.jpg', 89, 1),
('青花瓷茶杯套装', '传统青花瓷工艺，6杯套装', 168.00, 200, 6, 'blue_white_cup_set_1.jpg,blue_white_cup_set_2.jpg', 234, 1),
('陶瓷砂锅煲汤锅', '耐高温陶瓷砂锅，适合煲汤炖菜', 128.00, 80, 8, 'casserole_1.jpg,casserole_2.jpg', 67, 1),
('景德镇瓷碗套装', '家用陶瓷碗碟套装，10件装', 98.00, 150, 9, 'bowl_set_1.jpg,bowl_set_2.jpg', 189, 1),
('创意陶瓷花瓶', '现代简约风格陶瓷花瓶，适合装饰', 88.00, 120, 10, 'vase_1.jpg,vase_2.jpg', 123, 1),
('陶瓷装饰摆件', '中式风格陶瓷摆件，精美工艺', 158.00, 60, 11, 'ornament_1.jpg,ornament_2.jpg', 45, 1);

-- 插入管理员用户（密码：admin123）
INSERT INTO users (username, password, email, phone) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lE7wfP8n.NOkwO', 'admin@ceramicmall.com', '13800138000');

COMMIT;