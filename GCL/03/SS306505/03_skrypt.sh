#!/bin/bash
currenthour=$(date +"%H")
if ! (($currenthour%2)) ; then 
	echo "Even hour!"
	exit 0
else
	echo "Odd hour!"
	exit 1
fi
