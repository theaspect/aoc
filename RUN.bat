@echo off
call gradlew shadowJar

echo.
::echo day 1 part 1
::type tests\day1.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day1.Day1Part1

::echo day 1 part 2
::type tests\day1.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day1.Day1Part2

::echo day 2 part 1
::type tests\day2.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day2.Day2Part1

::echo day 2 part 2
::type tests\day2.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day2.Day2Part2

::echo day 3 part 1
::type tests\day3.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day3.Day3Part1

::echo day 3 part 2
::type tests\day3.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day3.Day3Part2

::echo day 4 part 1
::type tests\day4.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day4.Day4Part1

::echo day 4 part 2
::type tests\day4.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day4.Day4Part2

::echo day 5 part 1
::type tests\day5.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day5.Day5Part1

::echo day 5 part 2
::type tests\day5.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day5.Day5Part2

::echo day 6 part 1
::type tests\day6.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day6.Day6Part1

::echo day 6 part 2
::type tests\day6.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day6.Day6Part2

echo day 7 part 1
type tests\day7.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day7.Day7Part1

echo day 7 part 2
type tests\day7.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.day7.Day7Part2
