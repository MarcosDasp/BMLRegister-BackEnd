@echo off
SETLOCAL

REM ========================
REM PARÂMETROS
REM ========================
SET BACKUP_FILE=%1
SET DB_NAME=bmlregister
SET DB_USER=blm
SET DB_PASS=123

echo Restaurando backup: %BACKUP_FILE%

sqlcmd -S localhost -U %DB_USER% -P %DB_PASS% -Q "ALTER DATABASE [%DB_NAME%] SET SINGLE_USER WITH ROLLBACK IMMEDIATE"
sqlcmd -S localhost -U %DB_USER% -P %DB_PASS% -Q "RESTORE DATABASE [%DB_NAME%] FROM DISK='%BACKUP_FILE%' WITH REPLACE"
sqlcmd -S localhost -U %DB_USER% -P %DB_PASS% -Q "ALTER DATABASE [%DB_NAME%] SET MULTI_USER"

echo RESTORE concluído com sucesso!

ENDLOCAL
exit /b 0
