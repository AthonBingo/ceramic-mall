<template>
  <el-footer class="footer">
    <div class="footer-container">
      <div class="footer-content">
        <div class="footer-section">
          <h3>关于我们</h3>
          <ul>
            <li><router-link to="/about">公司简介</router-link></li>
            <li><router-link to="/contact">联系我们</router-link></li>
            <li><a href="#">加入我们</a></li>
          </ul>
        </div>
        <div class="footer-section">
          <h3>客户服务</h3>
          <ul>
            <li><a href="#">帮助中心</a></li>
            <li><a href="#">售后服务</a></li>
            <li><a href="#">配送说明</a></li>
          </ul>
        </div>
        <div class="footer-section">
          <h3>商务合作</h3>
          <ul>
            <li><a href="#">商家入驻</a></li>
            <li><a href="#">广告服务</a></li>
            <li><a href="#">友情链接</a></li>
          </ul>
        </div>
        <div class="footer-section contact">
          <h3>联系方式</h3>
          <p><el-icon><Phone /></el-icon> 客服热线：400-123-4567</p>
          <p><el-icon><Message /></el-icon> 邮箱：service@ceramicmall.com</p>
          <p><el-icon><Location /></el-icon> 地址：江西省景德镇市陶瓷园区</p>
          <div class="social-links">
            <el-icon class="social-icon"><ChatDotRound /></el-icon>
            <el-icon class="social-icon"><Platform /></el-icon>
            <el-icon class="social-icon"><Microphone /></el-icon>
          </div>
        </div>
      </div>
      <div class="footer-bottom">
        <p>&copy; 2024 陶瓷商城. All rights reserved. | 赣ICP备12345678号</p>
      </div>
    </div>
  </el-footer>
</template>

<script setup>
import { Phone, Message, Location, ChatDotRound, Platform, Microphone } from '@element-plus/icons-vue'
</script>

<style scoped>
.footer {
  background: #303133;
  color: #fff;
  padding: 0;
  margin-top: 60px;
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 20px 20px;
}

.footer-content {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 40px;
  margin-bottom: 30px;
}

.footer-section h3 {
  margin-bottom: 20px;
  font-size: 18px;
}

.footer-section ul {
  list-style: none;
  padding: 0;
}

.footer-section li {
  margin-bottom: 10px;
}

.footer-section a {
  color: #909399;
  text-decoration: none;
  transition: color 0.3s;
}

.footer-section a:hover {
  color: #409EFF;
}

.contact p {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  color: #909399;
}

.contact .el-icon {
  margin-right: 8px;
}

.social-links {
  margin-top: 20px;
  display: flex;
  gap: 15px;
}

.social-icon {
  font-size: 24px;
  color: #909399;
  cursor: pointer;
  transition: color 0.3s;
}

.social-icon:hover {
  color: #409EFF;
}

.footer-bottom {
  border-top: 1px solid #606266;
  padding-top: 20px;
  text-align: center;
  color: #909399;
  font-size: 14px;
}

@media (max-width: 768px) {
  .footer-content {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>