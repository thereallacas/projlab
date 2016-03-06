@echo off
setlocal EnableDelayedExpansion
set javaFiles=
cd src/main/
FOR /R %%f IN (*.java) DO call :addFile %%f
echo javac %javaFiles%
javac -g %javaFiles%
goto :eof

:addFile
set javaFiles=%javaFiles% %1
goto :eof

cd ../../