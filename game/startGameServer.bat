@echo off
title Game Server Console

:start
echo Starting L2J Game Server.
echo.

java -Xms512m -Xmx2048m -jar l2jdevs.jar

if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
goto end

:restart
echo.
echo Admin Restarted Game Server.
echo.
goto start

:error
echo.
echo Game Server Terminated Abnormally!
echo.

:end
echo.
echo Game Server Terminated.
echo.
pause