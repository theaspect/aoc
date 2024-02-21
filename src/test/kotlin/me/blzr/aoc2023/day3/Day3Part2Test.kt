package me.blzr.aoc2023.day3

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.blzr.aoc2023.day3.Day3Part2.extractNumbers
import me.blzr.aoc2023.day3.Day3Part2.extractSymbols
import me.blzr.aoc2023.day3.Day3Part2.filterMatches
import me.blzr.aoc2023.day3.Day3Part2.filterMatchesByLine
import me.blzr.aoc2023.day3.Day3Part2.findMatches
import me.blzr.aoc2023.day3.Day3Part2.parseLine
import me.blzr.aoc2023.day3.Day3Part2.process

class Day3Part2Test : StringSpec({
    "all" {
        listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        ).process() shouldBe 4361

        listOf(
            "467..114..",
            "...*......",
            "..35...633", //Modified
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            ".....*....",
            ".664*598..", // Modified
        ).process() shouldBe 4361

        listOf(
            "..___....__........___............*................___.........___..*............___....................*...___.....___...___.___.....#.....",
            ".....*..........................___.........%..___...*..............94...___.....&....................94...@.........%......................",
            "......___..................................___.&.....___..___.............$.........../___.....*.............___...............___.....___..",
        ).process() shouldBe 188
    }
    "filterMatches" {
        listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )
            .map(::parseLine)
            .filterMatches() shouldBe
                setOf(
                    Match(line = 0, num = 467, range = 0..2),
                    Match(line = 2, num = 35, range = 2..3),
                    Match(line = 2, num = 633, range = 6..8),
                    Match(line = 4, num = 617, range = 0..2),
                    Match(line = 6, num = 592, range = 2..4),
                    Match(line = 9, num = 664, range = 1..3),
                    Match(line = 7, num = 755, range = 6..8),
                    Match(line = 9, num = 598, range = 5..7)
                )
    }

    "filterMatches2" {
        listOf(
            ".*....*",
            ".94.94.",
            ".......",
        ).map(::parseLine)
            .filterMatches() shouldBe
                setOf(
                    Match(1, 94, 1..2),
                    Match(1, 94, 4..5),
                )
    }

    "findMatches" {
        val lines = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        ).map(::parseLine)

        findMatches(lines, 1, 3) shouldBe setOf(Match(0, 467, 0..2), Match(2, 35, 2..3))
        findMatches(lines, 3, 6) shouldBe setOf(Match(2, 633, 6..8))
        findMatches(lines, 4, 3) shouldBe setOf(Match(4, 617, 0..2))
        findMatches(lines, 5, 5) shouldBe setOf(Match(6, 592, 2..4))
        findMatches(lines, 8, 3) shouldBe setOf(Match(9, 664, 1..3))
        findMatches(lines, 8, 5) shouldBe setOf(Match(7, 755, 6..8), Match(9, 598, 5..7))
    }

    "extractSymbols" {
        extractSymbols("467..114..") shouldBe emptyList()
        extractSymbols("...*......") shouldBe listOf(3)
        extractSymbols("..35..633.") shouldBe emptyList()
        extractSymbols("......#...") shouldBe listOf(6)
        extractSymbols("617*......") shouldBe listOf(3)
        extractSymbols(".....+.58.") shouldBe listOf(5)
        extractSymbols("..592.....") shouldBe emptyList()
        extractSymbols("......755.") shouldBe emptyList()
        extractSymbols("...$.*....") shouldBe listOf(3, 5)
        extractSymbols(".664.598..") shouldBe emptyList()
    }

    "extractNumbers" {
        extractNumbers("467..114..") shouldBe setOf(467 to 0..2, 114 to 5..7)
        extractNumbers("...*......") shouldBe emptySet()
        extractNumbers("..35..633.") shouldBe setOf(35 to 2..3, 633 to 6..8)
        extractNumbers("......#...") shouldBe emptySet()
        extractNumbers("617*......") shouldBe setOf(617 to 0..2)
        extractNumbers(".....+.58.") shouldBe setOf(58 to 7..8)
        extractNumbers("..592.....") shouldBe setOf(592 to 2..4)
        extractNumbers("......755.") shouldBe setOf(755 to 6..8)
        extractNumbers("...$.*....") shouldBe emptySet()
        extractNumbers(".664.598..") shouldBe setOf(664 to 1..3, 598 to 5..7)
        // Additional line
        extractNumbers(".......598") shouldBe setOf(598 to 7..9)
    }

    /**
     * 123 *
     *  234*
     *   345
     *    456
     *     567
     *     *678
     *     * 789
     */
    "filterMatchesVertical" {
        setOf(
            1 to 1..3,
            2 to 2..4,
            3 to 3..5,
            4 to 4..6,
            5 to 5..7,
            6 to 6..8,
            7 to 7..9,
        ).filterMatchesByLine(5) shouldBe listOf(
            2 to 2..4,
            3 to 3..5,
            4 to 4..6,
            5 to 5..7,
            6 to 6..8,
        )
    }
})
