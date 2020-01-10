@echo off
title Starting Consumer

echo STEP 1 Maven install
call mvn clean install -f consumer/pom.xml

echo STEP 2 Build Docker image
call docker-compose build consumer

echo STEP 3 Deploy Docker images
call docker-compose up consumer 

pause
