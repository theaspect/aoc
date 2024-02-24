package me.blzr.aoc2023.day5

data class Range(
    val src: Long,
    val len: Long,
) {
    val range: LongRange = src..<src + len

    fun shift(delta: Long): Range = Range(src + delta, len)
}
