@echo off
setlocal enableextensions
pushd %~dp0

if exist data del data\items.txt
if exist data rmdir data

cd ..
call gradlew clean shadowJar

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jarloc=%%a
)

REM redirects standard error as well
java -jar %jarloc% < ..\..\text-ui-test\input.txt > ..\..\text-ui-test\ACTUAL.TXT

cd ..\..\text-ui-test

REM Skip the first two lines and exclude line 19 from ACTUAL.TXT before comparison
findstr /v /n "^" ACTUAL.TXT | findstr /r /v "^1: ^2: ^19:" > ACTUAL_TEMP.TXT

FC ACTUAL_TEMP.TXT EXPECTED.TXT
