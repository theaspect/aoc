package me.blzr.aoc2023.day5

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.alllex.parsus.parser.getOrThrow

class Day5Part1Test : StringSpec({
    "parse" {
        AlmanachGrammar().parse(
            """
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
        ).getOrThrow() shouldBe Almanac(
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
})
