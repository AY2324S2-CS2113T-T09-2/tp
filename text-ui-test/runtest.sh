#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd text-ui-test

# clean up data and log files
rm -rf data log

java -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input.txt &> ACTUAL.TXT

cp EXPECTED.TXT EXPECTED-UNIX.TXT

# Check the operating system and convert line endings accordingly
if [[ $(uname -s) == "Darwin" ]]; then
    # macOS
    sed -i '' 's/\r$//' EXPECTED-UNIX.TXT ACTUAL.TXT
elif [[ $(uname -s) == "Linux" ]]; then
    # Linux
    dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
elif [[ $(uname -s) == "CYGWIN"* || $(uname -s) == "MINGW"* ]]; then
    # Windows
    unix2dos -n EXPECTED-UNIX.TXT EXPECTED-UNIX-WINDOWS.TXT
    unix2dos -n ACTUAL.TXT ACTUAL-WINDOWS.TXT
fi

# compare the twenty-third line, after quote output onwards
diff <(tail -n +23 ACTUAL.TXT) EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi
