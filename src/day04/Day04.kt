package day04

import readInput

fun main() {

    fun parseRanges(range: String) =
        range.split(',')
            .flatMap { s ->
                s.split('-').map { it.toInt() }
            }.chunked(2)
            .sortedWith(compareBy<List<Int>> { it[0] }.thenByDescending { it[1] })
            .flatten()


    fun part1(input: List<String>): Int {
        return input.count { s ->
            val (a, b, c, d) = parseRanges(s)
            val range = a..b
            range.contains(c) && range.contains(d)
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { s ->
            val (a, b, c, d) = parseRanges(s)
            val range = a..b
            range.contains(c) || range.contains(d)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day04/Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("/day04/Day04")
    println(part1(input))
    println(part2(input))
}
