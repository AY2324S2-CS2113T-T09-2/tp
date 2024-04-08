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

findstr /v /n "^19:" ACTUAL.TXT > ACTUAL_TEMP.TXT
type EXPECTED.TXT > EXPECTED_TEMP.TXT
set /p line19=<ACTUAL.TXT
echo %line19%>> EXPECTED_TEMP.TXT

FC ACTUAL_TEMP.TXT EXPECTED_TEMP.TXT 
