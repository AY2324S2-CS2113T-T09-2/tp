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

REM Exclude line 19 from both ACTUAL.TXT and EXPECTED.TXT before running comparison
findstr /V /N "^" ACTUAL.TXT | findstr /V /C:"19:" > ACTUAL_TMP.TXT
findstr /V /N "^" EXPECTED.TXT | findstr /V /C:"19:" > EXPECTED_TMP.TXT

FC ACTUAL.TXT EXPECTED.TXT >NUL && ECHO Test passed! || Echo Test failed!
