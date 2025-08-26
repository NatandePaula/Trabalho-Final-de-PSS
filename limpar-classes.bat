@echo off
echo Limpando arquivos .class da pasta src...
echo.

REM Remove todos os arquivos .class da pasta src
for /r "src" %%f in (*.class) do (
    echo Removendo: %%f
    del "%%f"
)

echo.
echo Limpeza concluida!
echo Todos os arquivos .class foram removidos da pasta src.
echo.
pause
