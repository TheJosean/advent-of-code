package day01

import splitList
import readInput

fun main() {

    fun parseData(input: List<String>) =
        splitList(input)
            .map { elf -> elf.sumOf { it.toInt() } }

    fun part1(input: List<String>) = parseData(input).max()

    fun part2(input: List<String>) = parseData(input).sortedDescending().take(3).sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day01/Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("/day01/Day01")
    println(part1(input))
    println(part2(input))
}
