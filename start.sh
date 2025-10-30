#!/bin/bash

echo "========================================"
echo "期刊管理系统 - 快速启动脚本"
echo "========================================"
echo

# 检查Java环境
echo "[1/4] 检查Java环境..."
if ! command -v java &> /dev/null; then
    echo "❌ Java未安装或不在PATH中"
    echo "请安装JDK 17+并配置环境变量"
    exit 1
else
    echo "✅ Java环境正常"
fi

# 检查Maven环境
echo
echo "[2/4] 检查Maven环境..."
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven未安装或不在PATH中"
    echo "请安装Maven 3.6+并配置环境变量"
    exit 1
else
    echo "✅ Maven环境正常"
fi

# 检查Node.js环境
echo
echo "[3/4] 检查Node.js环境..."
if ! command -v node &> /dev/null; then
    echo "❌ Node.js未安装或不在PATH中"
    echo "请安装Node.js 16+并配置环境变量"
    exit 1
else
    echo "✅ Node.js环境正常"
fi

# 检查MySQL服务
echo
echo "[4/4] 检查MySQL服务..."
if ! command -v mysql &> /dev/null; then
    echo "❌ MySQL未安装或不在PATH中"
    echo "请安装MySQL 8.0+并启动服务"
    exit 1
else
    echo "✅ MySQL环境正常"
fi

echo
echo "========================================"
echo "环境检查完成，开始部署..."
echo "========================================"
echo

# 创建上传目录
if [ ! -d "uploads" ]; then
    mkdir -p uploads/articles/{main,supplementary,cover_letter}
    echo "✅ 创建上传目录"
fi

echo
echo "========================================"
echo "请选择启动模式："
echo "========================================"
echo "[1] 完整部署（数据库 + 后端 + 前端）"
echo "[2] 仅启动后端"
echo "[3] 仅启动前端"
echo "[4] 退出"
echo

read -p "请选择 (1-4): " choice

case $choice in
    1)
        echo
        echo "========================================"
        echo "开始完整部署..."
        echo "========================================"

        # 数据库初始化
        echo "[1/3] 初始化数据库..."
        mysql -u root -p journal < kstt-admin/src/main/resources/sql/journal_schema.sql
        if [ $? -eq 0 ]; then
            echo "✅ 数据库初始化完成"
        else
            echo "❌ 数据库初始化失败"
            echo "请检查MySQL连接和权限"
            exit 1
        fi

        # 后端编译
        echo
        echo "[2/3] 编译后端项目..."
        mvn clean package -DskipTests
        if [ $? -eq 0 ]; then
            echo "✅ 后端编译完成"
        else
            echo "❌ 后端编译失败"
            exit 1
        fi

        # 前端安装依赖
        echo
        echo "[3/3] 安装前端依赖..."
        npm install
        if [ $? -eq 0 ]; then
            echo "✅ 前端依赖安装完成"
        else
            echo "❌ 前端依赖安装失败"
            exit 1
        fi

        echo
        echo "========================================"
        echo "部署完成！启动服务..."
        echo "========================================"
        echo

        # 启动后端服务
        echo "启动后端服务..."
        java -jar kstt-admin/target/kstt-admin-1.0.0.jar &
        BACKEND_PID=$!

        # 等待后端启动
        sleep 5

        # 启动前端服务
        echo "启动前端服务..."
        npm run dev &
        FRONTEND_PID=$!

        echo
        echo "========================================"
        echo "服务启动完成！"
        echo "========================================"
        echo "后端地址: http://localhost:8080"
        echo "前端地址: http://localhost:5173"
        echo
        echo "管理员账户: admin / 123456"
        echo
        echo "按 Ctrl+C 停止服务..."
        echo

        # 等待进程结束
        wait $BACKEND_PID $FRONTEND_PID
        ;;

    2)
        echo
        echo "========================================"
        echo "启动后端服务..."
        echo "========================================"

        mvn clean package -DskipTests
        if [ $? -eq 0 ]; then
            java -jar kstt-admin/target/kstt-admin-1.0.0.jar
        else
            echo "❌ 后端编译失败"
            exit 1
        fi
        ;;

    3)
        echo
        echo "========================================"
        echo "启动前端服务..."
        echo "========================================"

        npm install
        if [ $? -eq 0 ]; then
            npm run dev
        else
            echo "❌ 前端依赖安装失败"
            exit 1
        fi
        ;;

    4)
        echo "退出脚本"
        exit 0
        ;;

    *)
        echo "无效选择，请重新运行脚本"
        exit 1
        ;;
esac