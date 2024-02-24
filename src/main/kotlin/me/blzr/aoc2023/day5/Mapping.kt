package me.blzr.aoc2023.day5

data class Mapping(
    val dst: Long,
    val src: Long,
    val len: Long,
) : Comparable<Mapping> {
    val delta = dst - src
    val range = src..<src + len

    fun tryTranslate(src: Long): Long? =
        when {
            src in range -> src + delta
            else -> null
        }

    override fun compareTo(other: Mapping): Int =
        src.compareTo(other.src)
}

fun Long.translate(mapping: List<Mapping>): Long =
    mapping.firstNotNullOfOrNull { it.tryTranslate(this@translate) } ?: this@translate

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
                        Long.MAX_VALUE - (last.src + last.len) + 1
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
