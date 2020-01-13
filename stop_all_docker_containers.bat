@echo off
title Stop all

echo Stopping all docker containers
FOR /f "tokens=*" %%i IN ('docker ps -q') DO docker stop %%i

pause
