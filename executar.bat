@echo off
echo Executando Sistema de Estoque...
echo.

rem Verificar se o diretório build existe
if not exist build (
    echo Diretorio build nao encontrado. Compilando primeiro...
    call compilar.bat
)

rem Executar o projeto
echo Executando sistema.views.MainMDIFrame...
java -cp build sistema.views.MainMDIFrame

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Erro ao executar. Verificando se as classes foram compiladas...
    echo.
    dir build\sistema\views\
    echo.
    pause
)
