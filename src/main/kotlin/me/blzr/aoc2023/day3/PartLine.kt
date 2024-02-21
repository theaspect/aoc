package me.blzr.aoc2023.day3

data class PartLine(
    val numbers: Set<Pair<Int, IntRange>>,
    val symbols: List<Int>
) {
    val hasSymbols: Boolean
        get() = symbols.isNotEmpty()
}
