@echo off
echo Compilando projeto...

rem Criar diretório para classes compiladas
if not exist build mkdir build

rem Compilar respeitando as dependências
echo Compilando utils...
javac -cp build -d build src/main/java/sistema/utils/*.java

echo Compilando models...
javac -cp build -d build src/main/java/sistema/models/*.java

echo Compilando daos...
javac -cp build -d build src/main/java/sistema/daos/*.java

echo Compilando controllers...
javac -cp build -d build src/main/java/sistema/controllers/*.java

echo Compilando views...
javac -cp build -d build src/main/java/sistema/views/*.java

if %errorlevel% equ 0 (
    echo.
    echo ✅ Compilacao concluida com sucesso!
    echo.
    echo Para executar o projeto:
    echo java -cp build sistema.views.TelaPrincipalForm
) else (
    echo.
    echo ❌ Erro na compilacao!
)
echo.
pause
