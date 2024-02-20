call gradlew shadowJar

echo.
echo day 1
type src\test\resources\me\blzr\aoc2023\day1.txt | java -cp build\libs\aoc2023-1.0-SNAPSHOT-all.jar me.blzr.aoc2023.Day1Kt
