package me.blzr.aoc2023.day3

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.blzr.aoc2023.day3.Day3Part1.extractNumbers
import me.blzr.aoc2023.day3.Day3Part1.extractSymbols
import me.blzr.aoc2023.day3.Day3Part1.filterMatches
import me.blzr.aoc2023.day3.Day3Part1.filterMatchesHorizontal
import me.blzr.aoc2023.day3.Day3Part1.filterMatchesVertical
import me.blzr.aoc2023.day3.Day3Part1.findMatches
import me.blzr.aoc2023.day3.Day3Part1.parseLine
import me.blzr.aoc2023.day3.Day3Part1.process

class Day3Part1Test : StringSpec({
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
                setOf(467, 35, 633, 617, 592, 755, 664, 598)
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

        findMatches(lines, 1, 3) shouldBe setOf(467 to 0..2, 35 to 2..3)
        findMatches(lines, 3, 6) shouldBe setOf(633 to 6..8)
        findMatches(lines, 4, 3) shouldBe setOf(617 to 0..2)
        findMatches(lines, 5, 5) shouldBe setOf(592 to 2..4)
        findMatches(lines, 8, 3) shouldBe setOf(664 to 1..3)
        findMatches(lines, 8, 5) shouldBe setOf(755 to 6..8, 598 to 5..7)
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
        extractNumbers("467..114..") shouldBe mapOf(467 to 0..2, 114 to 5..7)
        extractNumbers("...*......") shouldBe emptyMap<Int, IntRange>()
        extractNumbers("..35..633.") shouldBe mapOf(35 to 2..3, 633 to 6..8)
        extractNumbers("......#...") shouldBe emptyMap<Int, IntRange>()
        extractNumbers("617*......") shouldBe mapOf(617 to 0..2)
        extractNumbers(".....+.58.") shouldBe mapOf(58 to 7..8)
        extractNumbers("..592.....") shouldBe mapOf(592 to 2..4)
        extractNumbers("......755.") shouldBe mapOf(755 to 6..8)
        extractNumbers("...$.*....") shouldBe emptyMap<Int, IntRange>()
        extractNumbers(".664.598..") shouldBe mapOf(664 to 1..3, 598 to 5..7)
        // Additional line
        extractNumbers(".......598") shouldBe mapOf(598 to 7..9)
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
        mapOf(
            1 to 1..3,
            2 to 2..4,
            3 to 3..5,
            4 to 4..6,
            5 to 5..7,
            6 to 6..8,
            7 to 7..9,
        ).filterMatchesVertical(5) shouldBe listOf(
            2 to 2..4,
            3 to 3..5,
            4 to 4..6,
            5 to 5..7,
            6 to 6..8,
        )
    }

    /**
     *  234*
     *     *678
     */
    "filterMatchesHorizontal" {
        mapOf(
            1 to 1..3,
            2 to 2..4,
            3 to 3..5,
            4 to 4..6,
            5 to 5..7,
            6 to 6..8,
            7 to 7..9,
        ).filterMatchesHorizontal(5) shouldBe listOf(
            2 to 2..4,
            6 to 6..8,
        )
    }
})
