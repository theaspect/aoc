package me.blzr.aoc2023.day1

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.blzr.aoc2023.day1.Day1Part2.anyPrefix
import me.blzr.aoc2023.day1.Day1Part2.convertBackward
import me.blzr.aoc2023.day1.Day1Part2.convertForward
import me.blzr.aoc2023.day1.Day1Part2.getPairReplacing
import me.blzr.aoc2023.day1.Day1Part2.numbers
import me.blzr.aoc2023.day1.Day1Part2.process
import java.util.stream.Stream

class Day1Part2KtTest : StringSpec({
    "test prefix" {
        numbers.keys.anyPrefix("t") shouldBe true
        numbers.keys.anyPrefix("tw") shouldBe true
        numbers.keys.anyPrefix("two") shouldBe true
        numbers.keys.anyPrefix("twone") shouldBe false
    }

    "test convert forward" {
        convertForward("two1nine") shouldBe "219"
        convertForward("eightwothree") shouldBe "8wo3"
        convertForward("abcone2threexyz") shouldBe "abc123xyz"
        convertForward("xtwone3four") shouldBe "x2ne34"
        convertForward("4nineeightseven2") shouldBe "49872"
        convertForward("zoneight234") shouldBe "z1ight234"
        convertForward("7pqrstsixteen") shouldBe "7pqrst6teen"
    }

    "test convert backward" {
        convertBackward("two1nine") shouldBe "219"
        convertBackward("eightwothree") shouldBe "eigh23"
        convertBackward("abcone2threexyz") shouldBe "abc123xyz"
        convertBackward("xtwone3four") shouldBe "xtw134"
        convertBackward("4nineeightseven2") shouldBe "49872"
        convertBackward("zoneight234") shouldBe "zon8234"
        convertBackward("7pqrstsixteen") shouldBe "7pqrst6teen"
    }

    "get pairs" {
        getPairReplacing("two1nine") shouldBe 29
        getPairReplacing("eightwothree") shouldBe 83
        getPairReplacing("abcone2threexyz") shouldBe 13
        getPairReplacing("xtwone3four") shouldBe 24
        getPairReplacing("4nineeightseven2") shouldBe 42
        getPairReplacing("zoneight234") shouldBe 14
        getPairReplacing("7pqrstsixteen") shouldBe 76
    }

    "additional cases" {
        getPairReplacing("6oneightskl") shouldBe 68
        getPairReplacing("nleightwo7") shouldBe 87
    }

    "whole" {
        Stream.of(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
        ).process() shouldBe 281
    }

    "reverse" {
        "abc".reversed() shouldBe "cba"
    }
})
