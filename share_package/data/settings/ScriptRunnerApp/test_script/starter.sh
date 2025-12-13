#!/bin/bash

echo "The sample bash script has been called."
echo "\${0}: ${0}"
echo "\${1}: ${1}"
sleep 5

echo "The first step was started..."
sleep 5
echo "Check Point 1" > Check_Point_1.txt

echo "The second step was started..."
sleep 5
echo "Check Point 2" > Check_Point_2.txt

echo "The third step was started..."
sleep 5
echo "Check Point 3" > Check_Point_3.txt

echo "This is the end of the sample bash script."

