package day11

import readInput
import splitList

fun main() {

    data class Monkey(
        val id: Int,
        var items: MutableList<Int>,
        val divisibleBy: Int,
        val onTrue: Int,
        val onFalse: Int,
        val operation: (value: Long) -> Long,
        var inspects: Long = 0
    )

    fun parseMonkey(input: List<String>): Monkey {
        val (_, id) = input[0].split(' ')
        val (_, items) = input[1].split("Starting items: ")
        val (_, operation) = input[2].split("Operation: new = old ")
        val divisible = input[3].split(' ').last()
        val onTrue = input[4].split(' ').last()
        val onFalse = input[5].split(' ').last()
        return Monkey(
            id = id.dropLast(1).toInt(),
            items = items.split(", ").map { it.toInt() }.toMutableList(),
            divisibleBy = divisible.toInt(),
            onTrue = onTrue.toInt(),
            onFalse = onFalse.toInt(),
            operation = { value ->
                val (op, number) = operation.split(' ')
                val amount = number.toLongOrNull() ?: value
                when(op) {
                    "+" -> value + amount
                    "*" -> value * amount
                    else -> value
                }
            }
        )
    }

    fun getMonkeys(input: List<String>): Map<Int, Monkey> {
        return splitList(input)
            .map { parseMonkey(it) }.associateBy { it.id }
    }

    fun run(monkeys: Map<Int, Monkey>, rounds: Int, dividedBy: Int, module: Int): Long {
        (1..rounds).forEach { _ ->
            monkeys.values.forEach {
                it.inspects += it.items.size
                it.items.forEach { item ->
                    var worryLevel = it.operation.invoke(item.toLong()) / dividedBy
                    if (module != 0) worryLevel %= module
                    monkeys.getValue(if (worryLevel % it.divisibleBy == 0L) it.onTrue else it.onFalse).items.add(worryLevel.toInt())
                }
                it.items.clear()
            }
        }
        val (a, b) = monkeys.values.map { it.inspects }.sortedDescending().take(2)
        return a * b
    }

    fun part1(input: List<String>) = run(getMonkeys(input), 20, 3, 0)


    fun part2(input: List<String>) = getMonkeys(input).let { monkeys ->
            run(monkeys, 10000, 1, monkeys.values.map { it.divisibleBy }.reduce { acc, l -> acc * l}.toInt())
        }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day11/Day11_test")
    //check(part1(testInput) == 10605L)
    check(part2(testInput) == 2713310158L)

    val input = readInput("/day11/Day11")
    println(part1(input))
    println(part2(input))
}