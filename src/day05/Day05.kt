package day05

import readInput
import splitList

fun main() {

    data class Step(val amount: Int, val from: Int, val to: Int)

    fun transpose(matrix: List<List<Char>>): List<List<Char>> {
        matrix.filter { it.isNotEmpty() }.let { list ->
            return when {
                list.isNotEmpty() -> listOf(list.map { it.first() })
                    .plus(transpose(list.map { it.takeLast(it.size - 1) }))
                else -> emptyList()
            }
        }
    }

    fun parseStacks(stacks: List<String>) =
        transpose(stacks.map { it.toList() })
            .mapIndexedNotNull { index, list ->
                when {
                    index % 4 == 1 -> list
                    else -> null
               }
            }
            .associateBy(
                { it.last().digitToInt() },
                { it.subList(0, it.lastIndex).filter { l -> l.isLetter() }.reversed() }
            ).toMutableMap()

    fun parseSteps(steps: List<String>) =
        steps.map { s ->
            val (amount, from, to) = s.split(" ").mapNotNull { it.toIntOrNull() }
            Step(amount, from, to)
        }

    fun parseInput(input: List<String>):Pair<MutableMap<Int, List<Char>>, List<Step>> {
        val (rawStacks, rawSteps) =  splitList(input)
        return parseStacks(rawStacks) to parseSteps(rawSteps)
    }

    fun part1(input: List<String>): String {
        val (stacks, steps) =  parseInput(input)

        steps.forEach {
            stacks[it.to] = stacks.getValue(it.to).plus(stacks.getValue(it.from).takeLast(it.amount).reversed())
            stacks[it.from] = stacks.getValue(it.from).dropLast(it.amount)
        }
        return stacks.values.map { it.last() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val (stacks, steps) =  parseInput(input)
        steps.forEach {
            stacks[it.to] = stacks.getValue(it.to).plus(stacks.getValue(it.from).takeLast(it.amount))
            stacks[it.from] = stacks.getValue(it.from).dropLast(it.amount)
        }
        return stacks.values.map { it.last() }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day05/Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("/day05/Day05")
    println(part1(input))
    println(part2(input))
}
