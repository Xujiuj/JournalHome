# 期刊管理系统 - 最终版本

## 📋 系统概述

本项目是一个完整的学术期刊管理系统，基于Spring Boot + Vue.js + MySQL构建，实现了从投稿到发表的完整学术出版流程。系统参考了国际顶级期刊的标准，提供了专业级的学术出版解决方案。

## 🎯 核心特性

### 1. 智能投稿系统
- **多格式支持**: PDF、DOC、DOCX文档上传
- **元数据提取**: 自动识别论文标题、作者、摘要等信息
- **多文件管理**: 主文件、补充材料、投稿信等
- **完整表单**: 学术信息、资金支持、伦理声明等

### 2. 专业审稿流程
- **双盲审稿**: 支持匿名评审机制
- **多轮评审**: 支持多轮修改和评审
- **智能匹配**: 基于研究领域的审稿人自动推荐
- **进度跟踪**: 完整的审稿进度监控

### 3. 用户权限管理
- **多角色支持**: 访客、作者、评审员、编辑、主编、管理员
- **学术档案**: ORCID、ResearcherID、Google Scholar等集成
- **邮箱验证**: 安全的账户激活机制
- **权限控制**: 基于角色的精细权限管理

### 4. 内容管理系统
- **多期刊支持**: 支持多个期刊的独立管理
- **内容发布**: 灵活的文章发布和展示
- **通知系统**: 完整的公告和通知机制
- **FAQ管理**: 常见问题解答系统

## 🏗️ 技术架构

### 后端技术栈
```
Java 17 + Spring Boot 3.0
├── Spring Security (安全认证)
├── MyBatis Plus (ORM框架)
├── Apache PDFBox (PDF解析)
├── Apache POI (Word解析)
├── Redis (缓存)
├── MySQL 8.0 (数据库)
└── Swagger (API文档)
```

### 前端技术栈
```
Vue 3 + TypeScript
├── Element Plus (UI组件)
├── Tailwind CSS (样式)
├── Vue Router (路由)
├── Pinia (状态管理)
├── Axios (HTTP客户端)
└── Vite (构建工具)
```

## 📁 项目结构

```
journal/
├── kstt-admin/           # 管理后台服务
│   ├── src/main/
│   │   ├── java/com/kstt/
│   │   │   ├── controller/     # REST API控制器
│   │   │   ├── entity/         # 实体类
│   │   │   ├── mapper/         # 数据访问层
│   │   │   ├── service/        # 业务逻辑层
│   │   │   └── util/          # 工具类
│   │   └── resources/
│   │       ├── sql/           # 数据库脚本
│   │       └── application.properties
├── src/                 # 前端代码
│   ├── api/            # API接口
│   ├── assets/         # 静态资源
│   ├── components/     # 组件
│   ├── views/          # 页面视图
│   └── utils/          # 工具函数
└── docs/               # 文档
```

## 🚀 快速部署

### 环境要求
- **Java**: JDK 17+
- **Node.js**: 16.0+
- **MySQL**: 8.0+
- **Maven**: 3.6+

### 数据库初始化
   ```bash
# 1. 创建数据库
mysql -u root -p
CREATE DATABASE journal CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 2. 导入表结构和数据
mysql -u root -p journal < kstt-admin/src/main/resources/sql/journal_schema.sql
```

### 后端启动
   ```bash
# 1. 进入项目目录
cd journal

# 2. 编译打包
mvn clean package -DskipTests

# 3. 启动服务
java -jar kstt-admin/target/kstt-admin-1.0.0.jar
```

### 前端启动
   ```bash
# 1. 安装依赖
   npm install
   
# 2. 启动开发服务器
npm run dev

# 3. 构建生产版本
npm run build
```

### 前端配置说明
- `src/config/app.js`：统一的分页、缓存等运行时参数，可通过 `.env` 中的 `VITE_*` 变量覆盖。
- `src/store/modules/sysInfo.js`：集中获取系统信息与菜单，初始化时从后端加载后缓存于 Vuex，并对品牌/联系方式等不变量提供统一的派生 getter。
- `vite.config.js`：根据 `VITE_DEV_*` 环境变量自动配置开发服务器和代理，并对常用依赖进行预构建与手动分包以提升构建性能。
- `npm run bootstrap:env`（安装依赖时会自动执行）：在项目根目录生成 `.env.local`，可通过 `scripts/generate-env.mjs --force` 强制覆盖或自定义输出路径。
- 如需为不同环境设置变量，可参考脚本生成的 `.env.local` 拷贝为 `.env.development` / `.env.production`。

## 📊 数据库设计

### 核心表结构
```sql
-- 常量和枚举表
article_status                   # 文章状态
article_manuscript_type          # 稿件类型
article_submission_type          # 提交类型
article_file_type                # 文件类型
sys_user_academic_degree         # 学术学位

-- 核心业务表
sys_role                    # 角色表
sys_user                    # 用户表
sys_info                    # 期刊信息
sys_menu                    # 菜单权限
journal                     # 期刊卷期
article                     # 论文表
article_author             # 论文作者
article_process            # 论文流程
article_review_invitation  # 评审邀请
article_review             # 评审结果
article_submission_file    # 提交文件
article_suggested_reviewer # 建议审稿人
article_opposed_reviewer   # 避免审稿人
notice                     # 通知公告
contact_faq                # 常见问题
contact_message            # 联系消息
about_editorial_team       # 编辑团队
```

## 🔧 核心功能

### 1. 智能投稿流程
- **文档上传**: 支持PDF、DOC、DOCX格式
- **元数据提取**: 自动识别论文基本信息
- **完整表单**: 学术信息、资金支持、伦理声明等
- **多文件管理**: 主文件、补充材料、投稿信等

### 2. 专业审稿系统
- **匿名评审**: 保护评审人和作者隐私
- **多维度评分**: 质量、创新性、清晰度、重要性
- **流程跟踪**: 完整的审稿进度监控
- **智能匹配**: 基于研究领域的审稿人推荐

### 3. 用户权限管理
- **多角色支持**: 访客、作者、评审员、编辑、主编、管理员
- **学术档案**: ORCID、ResearcherID、Google Scholar等
- **邮箱验证**: 安全的账户激活机制

## 📈 系统特性

### 1. 高可用性
- **分布式部署**: 支持多实例部署
- **负载均衡**: 自动请求分发
- **故障恢复**: 自动错误恢复机制

### 2. 数据安全
- **加密存储**: 敏感数据加密存储
- **访问控制**: 基于角色的权限管理
- **审计日志**: 完整的操作记录

### 3. 用户体验
- **响应式设计**: 完美适配各种设备
- **直观界面**: 简洁明了的交互设计
- **实时反馈**: 即时的操作反馈

## 🎨 界面预览

### 首页
- 现代化的星空背景设计
- 动态滚动效果
- 期刊信息展示
- 最新文章推荐

### 投稿界面
- 智能文档上传
- 自动元数据提取
- 实时预览功能
- 完整的表单验证

### 审稿界面
- 清晰的评审流程
- 多维度评分系统
- 匿名评审支持
- 进度实时跟踪

## 🔄 API接口

### 认证相关
```
POST /auth/login           # 用户登录
POST /auth/register        # 用户注册
POST /auth/logout          # 用户登出
GET  /auth/me             # 获取用户信息
```

### 文章相关
```
GET    /articles/list      # 文章列表
POST   /articles/submit    # 提交文章
GET    /articles/{id}      # 文章详情
PUT    /articles/{id}      # 更新文章
DELETE /articles/{ids}     # 删除文章
```

### 审稿相关
```
GET    /articles/{id}/suggested-reviewers  # 获取建议审稿人
POST   /articles/{id}/suggested-reviewers  # 添加建议审稿人
GET    /articles/{id}/opposed-reviewers    # 获取避免审稿人
POST   /articles/{id}/opposed-reviewers    # 添加避免审稿人
```

## 📚 依赖库

### 后端依赖
```xml
<!-- Spring Boot核心 -->
spring-boot-starter-web
spring-boot-starter-security
spring-boot-starter-data-redis

<!-- 数据库相关 -->
mybatis-plus-boot-starter
mysql-connector-java
druid-spring-boot-starter

<!-- 文档处理 -->
pdfbox
poi-ooxml

<!-- 工具库 -->
hutool-all
fastjson
```

### 前端依赖
```json
{
  "vue": "^3.3.0",
  "element-plus": "^2.4.0",
  "axios": "^1.6.0",
  "vue-router": "^4.2.0",
  "pinia": "^2.1.0",
  "tailwindcss": "^3.3.0"
}
```

## 🔧 配置说明

### 数据库配置
```properties
# kstt-admin/src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/journal?useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=your_password
```

### 文件上传配置
```properties
# 上传文件保存路径
upload.path=/uploads/
# 支持的文件类型
upload.allowed-types=pdf,doc,docx
# 最大文件大小
upload.max-size=50MB
```

## 📋 测试数据

系统包含完整的测试数据：
- **10个枚举表**: 文章状态、稿件类型、学科领域等
- **6个角色**: 访客、作者、评审员、编辑、主编、管理员
- **4个测试用户**: 不同角色的完整用户信息
- **4篇示例文章**: 不同状态的论文数据
- **评审流程**: 完整的审稿邀请和评审记录
- **文件管理**: 文档上传和版本控制

## 🚨 注意事项

1. **数据备份**: 部署前务必备份重要数据
2. **权限设置**: 确保数据库用户有足够权限
3. **文件权限**: 确保上传目录有写权限
4. **网络安全**: 生产环境启用HTTPS
5. **性能监控**: 关注数据库和应用性能

## 📞 技术支持

- **开发团队**: system@cstj.example.com
- **问题反馈**: GitHub Issues
- **文档更新**: 参考项目文档

## 🔄 更新日志

### v2.0 (2025-01-27)
- ✅ 重新设计数据库表结构，消除冗余字段
- ✅ 引入枚举表和常量表，避免字符串存储
- ✅ 优化用户学术信息存储，使用关联表
- ✅ 简化文章表，移除冗余字段
- ✅ 完善审稿人管理，外键关联用户表
- ✅ 更新前端API，匹配新的后端结构

### v1.0 (2025-01-01)
- ✅ 完成基础系统架构
- ✅ 实现智能投稿流程
- ✅ 集成PDF/Word文档处理
- ✅ 完成用户权限管理
- ✅ 实现完整的审稿流程
- ✅ 优化前端用户体验

---

**系统状态**: 生产就绪 🚀
**维护状态**: 持续更新 🔄
**文档版本**: 2.0 📖