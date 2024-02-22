package me.blzr.aoc2023.day5

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.alllex.parsus.parser.getOrThrow
import me.blzr.aoc2023.day5.Day5Part1.process

class Day5Part1Test : StringSpec({
    val text = """
        seeds: 79 14 55 13
        
        seed-to-soil map:
        50 98 2
        52 50 48
        
        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15
        
        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4
        
        water-to-light map:
        88 18 7
        18 25 70
        
        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13
        
        temperature-to-humidity map:
        0 69 1
        1 0 69
        
        humidity-to-location map:
        60 56 37
        56 93 4
    """

    val almanac = AlmanachGrammar().parse(text).getOrThrow()

    "parse" {
        almanac shouldBe Almanac(
            seeds = listOf(79, 14, 55, 13),
            seedToSoil = listOf(
                Mapping(50, 98, 2),
                Mapping(52, 50, 48),
            ),
            soilToFertilizer = listOf(
                Mapping(0, 15, 37),
                Mapping(37, 52, 2),
                Mapping(39, 0, 15),
            ),
            fertilizerToWater = listOf(
                Mapping(49, 53, 8),
                Mapping(0, 11, 42),
                Mapping(42, 0, 7),
                Mapping(57, 7, 4),
            ),
            waterToLight = listOf(
                Mapping(88, 18, 7),
                Mapping(18, 25, 70),
            ),
            lightToTemperature = listOf(
                Mapping(45, 77, 23),
                Mapping(81, 45, 19),
                Mapping(68, 64, 13),
            ),
            temperatureToHumidity = listOf(
                Mapping(0, 69, 1),
                Mapping(1, 0, 69),
            ),
            humidityToLocation = listOf(
                Mapping(60, 56, 37),
                Mapping(56, 93, 4),
            )
        )
    }

    "translate" {
        almanac.translate() shouldBe listOf(
            Rule(
                seed = 79,
                soil = 81,
                fertilizer = 81,
                water = 81,
                light = 74,
                temperature = 78,
                humidity = 78,
                location = 82
            ),
            Rule(
                seed = 14,
                soil = 14,
                fertilizer = 53,
                water = 49,
                light = 42,
                temperature = 42,
                humidity = 43,
                location = 43
            ),
            Rule(
                seed = 55,
                soil = 57,
                fertilizer = 57,
                water = 53,
                light = 46,
                temperature = 82,
                humidity = 82,
                location = 86
            ),
            Rule(
                seed = 13,
                soil = 13,
                fertilizer = 52,
                water = 41,
                light = 34,
                temperature = 34,
                humidity = 35,
                location = 35
            ),
        )
    }

    "all" {
        text.process() shouldBe 35
    }
})
