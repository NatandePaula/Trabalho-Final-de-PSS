@echo off
echo ========================================
echo   SISTEMA DE ECONOMIA CIRCULAR
echo ========================================
echo.
echo Compilando o sistema...
echo.

REM Compilar todas as telas
javac -cp "lib/*;src/main/java" src/main/java/sistema/views/*.java

if %ERRORLEVEL% NEQ 0 (
    echo ERRO na compilacao!
    pause
    exit /b 1
)

echo Compilacao concluida com sucesso!
echo.
echo Executando o sistema...
echo.

REM Executar o sistema
java -cp "lib/*;src/main/java" sistema.views.TelaLogin

echo.
echo Sistema encerrado.
pause
