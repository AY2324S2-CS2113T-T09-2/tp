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
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
# compare the thirteenth line, after logger output onwards
diff <(tail -n +13 ACTUAL.TXT) EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi
