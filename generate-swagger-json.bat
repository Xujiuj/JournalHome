@echo off

REM 等待服务启动
timeout /t 5 /nobreak > nul

REM 下载Swagger JSON文件
curl -o swagger.json http://localhost:10086/api-docs

if %errorlevel% equ 0 (
    echo Swagger JSON文件已生成: swagger.json
    echo 文件大小:
    dir swagger.json
) else (
    echo 生成失败，请确保服务正在运行
)

pause
