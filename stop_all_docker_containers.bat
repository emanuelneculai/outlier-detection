@echo off
title Stop all

call docker stop $(docker ps -a -q)

pause
