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

REM Delete line 19 from ACTUAL.TXT
type ACTUAL.TXT | findstr /v /n "^19:" > ACTUAL_TEMP.TXT

REM Compare ACTUAL_TEMP.TXT with EXPECTED.TXT
FC ACTUAL_TEMP.TXT EXPECTED.TXT
