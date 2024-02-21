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

                else -> {
                    from = i + 1
                }
            }
        }

        // Don't forget about buffer if the number ends the line
        if (from != line.length) {
            result[buf] = from..<line.length
        }

        return result
    }

    fun List<PartLine>.filterMatches(): Set<Int> =
        this
            .flatMapIndexed { line, partLine ->
                partLine.symbols.flatMap { symbol ->
                    findMatches(
                        lines = this@filterMatches,
                        line = line,
                        symbolPos = symbol
                    )
                }
            }
            .distinct()
            .map(Pair<Int, IntRange>::first)
            // For ease of testing
            .toSet()

    fun Map<Int, IntRange>.filterMatchesVertical(pos: Int) =
        this.entries
            .filter { (_, range) -> pos in range.inc() }
            .map { (num, range) -> num to range }

    fun Map<Int, IntRange>.filterMatchesHorizontal(pos: Int) =
        this.entries
            .filter { (_, range) ->
                pos == range.first - 1 || pos == range.last + 1
            }
            .map { (num, range) -> num to range }

    fun IntRange.inc() = IntRange(this.first - 1, this.last + 1)

    fun findMatches(lines: List<PartLine>, line: Int, symbolPos: Int): Set<Pair<Int, IntRange>> {
        val result: MutableSet<Pair<Int, IntRange>> = mutableSetOf()

        // Check above
        if (line > 0) {
            result.addAll(lines[line - 1].numbers.filterMatchesVertical(symbolPos))
        }

        // Check the same line
        result.addAll(lines[line].numbers.filterMatchesVertical(symbolPos))

        // Check the line below
        if (line < lines.size - 1) {
            result.addAll(lines[line + 1].numbers.filterMatchesVertical(symbolPos))
        }

        return result
    }
}

data class PartLine(
    val numbers: Map<Int, IntRange>,
    val symbols: List<Int>
) {
    val hasSymbols: Boolean
        get() = symbols.isNotEmpty()
}
