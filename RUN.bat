@echo off
call gradlew shadowJar

echo.
echo day 1 part 1
type tests\day1.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day1.Day1Part1

echo day 1 part 2
type tests\day1.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day1.Day1Part2

echo day 2 part 1
type tests\day2.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day2.Day2Part1

echo day 2 part 2
type tests\day2.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day2.Day2Part2

echo day 3 part 1
type tests\day3.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day3.Day3Part1
