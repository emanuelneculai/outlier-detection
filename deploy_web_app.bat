@echo off
title Starting Web App

echo STEP 1 Maven install
call mvn clean install -f app-webserver/pom.xml

echo STEP 2 Build Docker image
call docker-compose build app-webserver

echo STEP 3 Deploy Docker images
call docker-compose up app-webserver 

pause
