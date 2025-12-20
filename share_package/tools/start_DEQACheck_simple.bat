@echo off
chcp 65001 > nul

REM Require Java 17

REM Get the directory where this script is located
set SCRIPT_DIR=%~dp0

REM Run the DEQACheck JAR with the first argument
java -jar "%SCRIPT_DIR%DEQACheck-v20251109-all.jar" %1

pause
