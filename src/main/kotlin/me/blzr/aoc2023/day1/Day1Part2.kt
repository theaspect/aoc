package me.blzr.aoc2023.day1

import java.util.stream.Stream

/**
 * 54194 is too low
 * 54208 is correct
 */
object Day1Part2 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lines().process())
    }

    fun Stream<String>.process(): Int =
        this.map { getPairReplacing(it) }.reduce(0, Int::plus)

    fun getPairReplacing(s: String): Int = convertForward(s) * 10 + convertBackward(s)

    val numbers = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    val numbersReversed =
        numbers.mapKeys { (k, _) -> k.reversed() }

    fun convertForward(s: String): Int {
        val buffer = StringBuilder()

        for (c in s) {
            if (c in '0'..'9') return c - '0'

            buffer.append(c)

            if (!numbers.keys.anyPrefix(buffer)) {
                while (buffer.isNotEmpty() && !numbers.keys.anyPrefix(buffer)) {
                    buffer.shl()
                }
            } else if (buffer.toString() in numbers.keys) {
                return numbers[buffer.toString()]!!
            }
        }

        // Put remaining buffer
        // We assume correct value
        return numbers[buffer.toString()]!!
    }

    fun convertBackward(s: String): Int {
        val buffer = StringBuilder()

        for (c in s.reversed()) {
            if (c in '0'..'9') return c - '0'

            buffer.append(c)

            if (!numbersReversed.keys.anyPrefix(buffer)) {
                while (buffer.isNotEmpty() && !numbersReversed.keys.anyPrefix(buffer)) {
                    buffer.shl()
                }
            } else if (buffer.toString() in numbersReversed.keys) {
                return numbersReversed[buffer.toString()]!!
            }
        }

        // Put remaining buffer
        // We assume correct value
        return numbersReversed[buffer.toString()]!!
    }

    fun Set<String>.anyPrefix(prefix: CharSequence): Boolean =
        this.any { it.startsWith(prefix) }

    infix fun StringBuilder.shiftTo(target: StringBuilder) {
        if (this.isEmpty()) return

        target.append(this[0])

        this.delete(0, 1)
    }

    fun StringBuilder.shl() = this.delete(0, 1)

    fun String.reversed(): String = StringBuilder(this).reverse().toString()
}
