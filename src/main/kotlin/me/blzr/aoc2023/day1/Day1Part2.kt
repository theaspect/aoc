package me.blzr.aoc2023.day1

import java.util.stream.Stream

/**
 * 54194 is too low
 */
object Day1Part2 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lines().process())
    }

    fun Stream<String>.process(): Int =
        this.map { getPairReplacing(it) }.reduce(0, Int::plus)

    fun getPairReplacing(s: String): Int {
        if (convertBackward(s) != convertForward(s)) println(s)
        val first = convertForward(s).find { it in '0'..'9' }!! - '0'
        val last = convertBackward(s).findLast { it in '0'..'9' }!! - '0'
        return first * 10 + last
    }

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

    fun convertForward(s: String): String {
        val newLine = StringBuilder()
        val buffer = StringBuilder()

        for (c in s) {
            buffer.append(c)

            if (!numbers.keys.anyPrefix(buffer)) {
                while (buffer.isNotEmpty() && !numbers.keys.anyPrefix(buffer)) {
                    buffer shift newLine
                }
            } else if (buffer.toString() in numbers.keys) {
                newLine.append(numbers[buffer.toString()])
                buffer.clear()
            }
        }

        // Put remaining buffer
        newLine.append(buffer)

        return newLine.toString()
    }

    fun convertBackward(s: String): String {
        val newLine = StringBuilder()
        val buffer = StringBuilder()

        for (c in s.reversed()) {
            buffer.append(c)

            if (!numbersReversed.keys.anyPrefix(buffer)) {
                while (buffer.isNotEmpty() && !numbersReversed.keys.anyPrefix(buffer)) {
                    buffer shift newLine
                }
            } else if (buffer.toString() in numbersReversed.keys) {
                newLine.append(numbersReversed[buffer.toString()])
                buffer.clear()
            }
        }

        // Put remaining buffer
        newLine.append(buffer)

        return newLine.toString().reversed()
    }

    fun Set<String>.anyPrefix(prefix: CharSequence): Boolean =
        this.any { it.startsWith(prefix) }

    infix fun StringBuilder.shift(target: StringBuilder) {
        if (this.isEmpty()) return

        target.append(this[0])

        this.delete(0, 1)
    }

    fun String.reversed(): String = StringBuilder(this).reverse().toString()
}
