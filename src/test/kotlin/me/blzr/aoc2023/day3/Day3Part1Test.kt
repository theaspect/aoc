package me.blzr.aoc2023.day3

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.blzr.aoc2023.day3.Day3Part1.extractNumbers
import me.blzr.aoc2023.day3.Day3Part1.extractSymbols

class Day3Part1Test : StringSpec({
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
})
