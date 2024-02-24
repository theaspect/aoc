package me.blzr.aoc2023.day5

data class Mapping(
    val dst: Long,
    val src: Long,
    val len: Long,
) : Comparable<Mapping> {
    val delta = dst - src
    val longRange = src..<src + len
    val range: Range = Range(src, len)

    fun tryTranslate(src: Long): Long? =
        when {
            src in longRange -> src + delta
            else -> null
        }

    override fun compareTo(other: Mapping): Int =
        src.compareTo(other.src)
}

fun Long.translate(mapping: List<Mapping>): Long =
    mapping.firstNotNullOfOrNull { it.tryTranslate(this@translate) } ?: this@translate

/**
 * Shift each range by each mapping probably producing null or empty list
 */
fun List<Range>.translate(mapping: List<Mapping>): List<Range> {
    val translated: MutableList<Range> = mutableListOf()

    for (r: Range in this) {
        for (m: Mapping in mapping) {
            (r overlap m.range)?.shift(m.delta)?.let { translated.add(it) }
        }
    }

    return translated.sortedBy { it.src }
}

infix fun Range.overlap(other: Range): Range? {
    val thisRange: LongRange = this.range
    val otherRange: LongRange = other.range

    return when {
        // this: 4..6
        // that: 1..3
        thisRange.first > otherRange.last ||
                // this: 1..3
                // that: 4..6
                thisRange.last < otherRange.first -> null

        // this: 1..3
        // that: 2..4

        // this: 1..5
        // that: 2..3
        thisRange.first > otherRange.first ->
            Range(
                thisRange.first, minOf(thisRange.last, otherRange.last) - thisRange.first + 1
            )

        // this: 2..4
        // that: 1..3

        // this: 2..3
        // that: 1..5
        else -> Range(
            otherRange.first, minOf(thisRange.last, otherRange.last) - otherRange.first + 1
        )
    }
}

/**
 * We assume range from 0..Int.MAX_VALUE
 */
fun List<Mapping>.fillUp(): List<Mapping> {
    val filled: MutableList<Mapping> = mutableListOf()

    val sortedMappings: List<Mapping> = this.sorted()

    when {
        // Technically it should be MAX_VALUE+1
        sortedMappings.isEmpty() -> filled.add(Mapping(0, 0, Long.MAX_VALUE))
        sortedMappings.first().src > 0 -> filled.add(Mapping(0, 0, sortedMappings.first().src))
        else -> {
            /* Do nothing */
        }
    }

    for (i in sortedMappings.indices) {
        filled.add(sortedMappings[i])

        val last: Mapping = sortedMappings.last()
        when {
            sortedMappings[i] == last && last.src + last.len < Long.MAX_VALUE ->
                filled.add(
                    Mapping(
                        last.src + last.len,
                        last.src + last.len,
                        Long.MAX_VALUE - (last.src + last.len)
                    )
                )

            sortedMappings[i] != last
                    && (sortedMappings[i + 1].src - (sortedMappings[i].src + sortedMappings[i].len) > 0) -> filled.add(
                Mapping(
                    sortedMappings[i].src + sortedMappings[i].len,
                    sortedMappings[i].src + sortedMappings[i].len,
                    sortedMappings[i + 1].src - (sortedMappings[i].src + sortedMappings[i].len)
                )
            )

            else -> {
                /* Do nothing */
            }
        }
    }

    return filled
}
