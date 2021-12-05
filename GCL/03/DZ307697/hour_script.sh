#!/usr/bin/env bash

hour=$(date +"%H")
result=$(($hour % 2))

echo "godzina: ${hour}"
echo "result: ${result}"

if [[ $result == 0 ]]; then
    echo "godzina jest parzysta"
else
  echo "godzina jest nieparzysta"
  exit 1
fi
