@echo off
title Install Application

echo STEP 1 Maven install
call mvn clean install 

echo STEP 2 Build Docker images
call docker-compose build

pause
