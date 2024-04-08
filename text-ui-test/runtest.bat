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

REM Exclude the 19th line from ACTUAL_TEMP.TXT before comparison
set "line=0"
(for /f "delims=" %%i in (ACTUAL.TXT) do (
    set /a "line+=1"
    if "!line!" neq "19" echo %%i
)) > ACTUAL_TEMP.TXT

FC ACTUAL_TEMP.TXT EXPECTED.TXT
