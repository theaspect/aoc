package me.blzr.aoc2023.day3

import java.io.File

/**
 * 327998 is too low
 * 546563
 */
object Day3Part2 {
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
        val results: List<List<Match>> = this
            .map(::parseLine)
            .filterMatches()

        renderDebug(this, results)

        return results.sumOf { (a, b) -> a.num * b.num }
    }

    fun renderDebug(lines: List<String>, matches: List<List<Match>>) {
        val debug: MutableList<String> = lines.toMutableList()

        for (gear in matches) {
            for ((line, num, range) in gear) {
                try {
                    debug[line] = debug[line].replaceRange(range, "_".repeat(range.last - range.first + 1))
                } catch (e: Exception) {
                    println("Incorrect match $line, $num, $range")
                }
            }
        }

        File("day3part2.debug").writeText(debug.joinToString("\n"))
    }

    fun parseLine(line: String): PartLine =
        PartLine(
            extractNumbers(line),
            extractSymbols(line)
        )

    fun extractSymbols(line: String): List<Int> =
        line.mapIndexedNotNull { index, c ->
            when {
                c == '*' -> index
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

    fun List<PartLine>.filterMatches(): List<List<Match>> =
        this
            .flatMapIndexed { line, partLine ->
                partLine.symbols.mapNotNull { symbol ->
                    findMatches(
                        lines = this@filterMatches,
                        line = line,
                        symbolPos = symbol
                    )
                }
            }

    fun Set<Pair<Int, IntRange>>.filterMatchesByLine(pos: Int) =
        this
            .filter { (_, range) -> pos in range.inc() }
            .map { (num, range) -> num to range }

    fun IntRange.inc() = IntRange(this.first - 1, this.last + 1)

    fun findMatches(lines: List<PartLine>, line: Int, symbolPos: Int): List<Match>? {
        val result: MutableSet<Match> = mutableSetOf()

        // Check above
        if (line > 0) {
            result.addAll(
                lines[line - 1].numbers.filterMatchesByLine(symbolPos).map { Match(line - 1, it.first, it.second) })
        }

        // Check the same line
        result.addAll(lines[line].numbers.filterMatchesByLine(symbolPos).map { Match(line, it.first, it.second) })

        // Check the line below
        if (line < lines.size - 1) {
            result.addAll(
                lines[line + 1].numbers.filterMatchesByLine(symbolPos).map { Match(line + 1, it.first, it.second) })
        }

        // Return only gears
        return when {
            result.size != 2 -> null
            else -> result.toList()
        }
    }
}


