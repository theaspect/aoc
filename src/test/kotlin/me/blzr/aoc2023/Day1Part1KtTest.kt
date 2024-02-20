package me.blzr.aoc2023

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day1Part1KtTest : StringSpec({
    "lines" {
        getPair("1abc2") shouldBe 12
        getPair("pqr3stu8vwx") shouldBe 38
        getPair("a1b2c3d4e5f") shouldBe 15
        getPair("treb7uchet") shouldBe 77
    }

    "whole" {
        sequenceOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet",
        ).process() shouldBe 142
    }
})
