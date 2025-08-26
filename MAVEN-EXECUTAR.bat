@echo off
title MAVEN + EXECUTAR SISTEMA
color 0A

echo.
echo ========================================
echo   SISTEMA DE ECONOMIA CIRCULAR
echo ========================================
echo   Compilando com Maven e Executando
echo ========================================
echo.

REM Compilar com Maven
echo [1/3] Compilando com Maven...
call mvnw.cmd clean compile package
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERRO na compilacao!
    pause
    exit /b 1
)

echo.
echo [2/3] Compilacao concluida com sucesso!
echo.

REM Verificar JAR
echo [3/3] Verificando JAR...
if not exist "target\sistema-loja-vestuario-1.0.0.jar" (
    echo ERRO: JAR nao foi criado!
    pause
    exit /b 1
)

echo JAR encontrado! Executando sistema...
echo.

REM Executar sistema
java -jar target\sistema-loja-vestuario-1.0.0.jar

echo.
echo Sistema encerrado.
pause
