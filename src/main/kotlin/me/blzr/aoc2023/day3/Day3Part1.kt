package me.blzr.aoc2023.day3

import java.io.File

/**
 * 327998 is too low
 * 546563
 */
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
    fun List<String>.process(): Int {
        val results: Set<Match> = this
            .map(::parseLine)
            .filterMatches()

        renderDebug(this, results)

        return results
            .map(Match::num)
            .sum()
    }

    fun renderDebug(lines: List<String>, matches: Set<Match>) {
        val debug: MutableList<String> = lines.toMutableList()

        for ((line, num, range) in matches) {
            try {
                debug[line] = debug[line].replaceRange(range, "_".repeat(range.last - range.first + 1))
            } catch (e: Exception) {
                println("Incorrect match $line, $num, $range")
            }
        }

        File("task3.debug").writeText(debug.joinToString("\n"))
    }

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

    fun extractNumbers(line: String): Set<Pair<Int, IntRange>> {
        val result: MutableSet<Pair<Int, IntRange>> = mutableSetOf()
        var from = 0
        var buf = 0

        for (i: Int in line.indices) {
            when {
                line[i] in '0'..'9' -> {
                    buf = buf * 10 + (line[i] - '0')
                }

                from != i -> {
                    result.add(buf to from..<i)
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
            result.add(buf to from..<line.length)
        }

        return result
    }

    fun List<PartLine>.filterMatches(): Set<Match> =
        this
            .flatMapIndexed { line, partLine ->
                partLine.symbols.flatMap { symbol ->
                    findMatches(
                        lines = this@filterMatches,
                        line = line,
                        symbolPos = symbol
                    )
                }
            }.toSet()

    fun Set<Pair<Int, IntRange>>.filterMatchesVertical(pos: Int) =
        this
            .filter { (_, range) -> pos in range.inc() }
            .map { (num, range) -> num to range }

    fun Map<Int, IntRange>.filterMatchesHorizontal(pos: Int) =
        this.entries
            .filter { (_, range) ->
                pos == range.first - 1 || pos == range.last + 1
            }
            .map { (num, range) -> num to range }

    fun IntRange.inc() = IntRange(this.first - 1, this.last + 1)

    fun findMatches(lines: List<PartLine>, line: Int, symbolPos: Int): Set<Match> {
        val result: MutableSet<Match> = mutableSetOf()

        // Check above
        if (line > 0) {
            result.addAll(
                lines[line - 1].numbers.filterMatchesVertical(symbolPos).map { Match(line - 1, it.first, it.second) })
        }

        // Check the same line
        result.addAll(lines[line].numbers.filterMatchesVertical(symbolPos).map { Match(line, it.first, it.second) })

        // Check the line below
        if (line < lines.size - 1) {
            result.addAll(
                lines[line + 1].numbers.filterMatchesVertical(symbolPos).map { Match(line + 1, it.first, it.second) })
        }

        return result
    }
}

data class PartLine(
    val numbers: Set<Pair<Int, IntRange>>,
    val symbols: List<Int>
) {
    val hasSymbols: Boolean
        get() = symbols.isNotEmpty()
}

data class Match(
    val line: Int,
    val num: Int,
    val range: IntRange,
)
