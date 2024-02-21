package me.blzr.aoc2023.day3

object Day3Part1 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lines().toList().process())
    }

    /**
     * 467..114..
     * ...*......
     * ..35..633.
     * ......#...
     * 617*......
     * .....+.58.
     * ..592.....
     * ......755.
     * ...$.*....
     * .664.598..
     */
    fun List<String>.process(): Int =
        this
            .map(::parseLine)
            .filterMatches()
            .sum()

    fun parseLine(line: String): PartLine =
        PartLine(
            extractNumbers(line),
            extractSymbols(line)
        )

    fun extractSymbols(line: String): List<Int> =
        line.mapIndexedNotNull { index, c ->
            when {
                c !in '0'..'9' && c != '.' -> index
                else -> null
            }
        }

    fun extractNumbers(line: String): Map<Int, IntRange> {
        val result: MutableMap<Int, IntRange> = mutableMapOf()
        var from = 0
        var buf = 0

        for (i: Int in line.indices) {
            when {
                line[i] in '0'..'9' -> {
                    buf = buf * 10 + (line[i] - '0')
                }

                from != i -> {
                    result[buf] = from..<i
                    from = i + 1
                    buf = 0
                }
            }
        }

        // Don't forget about buffer if the number ends the line
        if (from != line.length) {
            result[buf] = from..<line.length
        }

        return result
    }

    fun List<PartLine>.filterMatches(): List<Int> {
        // Iterate over lines
        // Iterate over symbols
        // Check if number is in range
        // filter
        TODO()
    }
}

data class PartLine(
    val numbers: Map<Int, IntRange>,
    val symbols: List<Int>
)
