package day_02

import println
import readInput
import kotlin.math.abs

fun main() {
    val testInput = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent().split("\n")

    fun part1(input: List<String>): Int {
        return input.count { line ->
            val levels = line.split(" ").map { it.toInt() }
            isSafeReport(levels)
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { line ->
            val levels = line.split(" ").map { it.toInt() }

            if (isSafeReport(levels)) {
                true
            } else {
                levels.indices.any { index ->
                    val modifiedLevels = levels.filterIndexed { i, _ -> i != index }
                    isSafeReport(modifiedLevels)
                }
            }
        }
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("day_02/Day02")
    part1(input).println()
    part2(input).println()
}

private fun isSafeReport(levels: List<Int>): Boolean {
    val isIncreasing = levels.zipWithNext().all { (a, b) -> a < b }
    val isDecreasing = levels.zipWithNext().all { (a, b) -> a > b }
    val hasValidDiff = levels.zipWithNext().all { (a, b) -> abs(a - b) <= 3 }
    return (isIncreasing || isDecreasing) && hasValidDiff
}
