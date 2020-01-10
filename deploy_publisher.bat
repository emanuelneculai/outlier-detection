@echo off
title Starting Publisher

echo STEP 1 Maven install
call mvn clean install -f publisher/pom.xml

echo STEP 2 Build Docker image
call docker-compose build publisher

echo STEP 3 Deploy Docker images
call docker-compose up publisher 

pause
