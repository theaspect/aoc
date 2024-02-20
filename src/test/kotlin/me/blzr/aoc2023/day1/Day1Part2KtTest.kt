package me.blzr.aoc2023.day1

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day1Part2KtTest : StringSpec({
    "test prefix" {
        numbers.keys.anyPrefix("t") shouldBe true
        numbers.keys.anyPrefix("tw") shouldBe true
        numbers.keys.anyPrefix("two") shouldBe true
        numbers.keys.anyPrefix("twone") shouldBe false
    }

    "test convert" {
        convert("two1nine") shouldBe "219"
        convert("eightwothree") shouldBe "8wo3"
        convert("abcone2threexyz") shouldBe "abc123xyz"
        convert("xtwone3four") shouldBe "x2ne34"
        convert("4nineeightseven2") shouldBe "49872"
        convert("zoneight234") shouldBe "z1ight234"
        convert("7pqrstsixteen") shouldBe "7pqrst6teen"
    }

})
