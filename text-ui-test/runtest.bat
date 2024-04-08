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

REM Delete contents of line 19 in ACTUAL.TXT and replace it with a blank line
for /f "tokens=1,* delims=:" %%A in ('findstr /n "^" ACTUAL.TXT ^| findstr /v /b "19:"') do echo(%%B > ACTUAL_TEMP.TXT

REM Compare ACTUAL_TEMP.TXT with EXPECTED.TXT
FC ACTUAL_TEMP.TXT EXPECTED.TXT
