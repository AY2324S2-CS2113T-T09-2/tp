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

REM Skip the first 20 lines and copy the rest of the file
more +20 ACTUAL.TXT > ACTUAL_TEMP.TXT
more +20 EXPECTED.TXT > EXPECTED_TEMP.TXT

FC ACTUAL_TEMP.TXT EXPECTED_TEMP.TXT >NUL && ECHO Test passed! || Echo Test failed!
