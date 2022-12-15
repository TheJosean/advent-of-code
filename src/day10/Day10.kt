package day10

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val cycles = listOf(20, 60, 100, 140, 180, 220)
        var cycle = 0
        var x = 1
        return input.sumOf {
            var result = 0
            cycle++
            when (it) {
                "noop" -> {
                    if (cycles.contains(cycle)) result = x * cycle
                }
                else -> {
                    if (cycles.contains(cycle)) result = x * (cycle)
                    cycle++
                    if (cycles.contains(cycle)) result = x * (cycle)
                    x += it.split(' ').last().toInt()
                }
            }
            result
        }
    }

    fun part2(input: List<String>): String {

        fun print(x: Int, cycle: Int, cycles: List<Int>): String {
            var result = if (((x - 1)..(x + 1)).contains((cycle - 1) % 40)) "#" else "."
            if (cycles.contains(cycle)) result += "\n"
            return result
        }

        val cycles = listOf(40, 80, 120, 160, 200, 240)
        var cycle = 0
        var x = 1
        return input.joinToString("") {
            cycle++
            when (it) {
                "noop" -> {
                    print(x, cycle, cycles)
                }
                else -> {
                    val result = print(x, cycle++, cycles) + print(x, cycle, cycles)
                    x += it.split(' ').last().toInt()
                    result
                }
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day10/Day10_test")
    check(part1(testInput) == 13140)
    check(part2(testInput) ==
            "##..##..##..##..##..##..##..##..##..##..\n" +
            "###...###...###...###...###...###...###.\n" +
            "####....####....####....####....####....\n" +
            "#####.....#####.....#####.....#####.....\n" +
            "######......######......######......####\n" +
            "#######.......#######.......#######.....\n" )

    val input = readInput("/day10/Day10")
    println(part1(input))
    println(part2(input))
}