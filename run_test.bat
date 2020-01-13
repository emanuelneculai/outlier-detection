@echo off
title Running Test

if not exist test-suite\target\com\emi\demo\test mkdir test-suite\target\com\emi\demo\test

call javac -d test-suite\target test-suite\src\com\emi\demo\test\TestMainClass.java 
call java -classpath test-suite\target\com\emi\demo\test\; com.emi.demo.test.TestMainClass

pause