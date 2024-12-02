package day_01

import println
import readInput
import kotlin.math.abs

fun main() {
    val testInput = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent().split("\n")

    fun part1(input: List<String>): Int {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        // Split each line and populate left and right lists
        input.forEach { line ->
            val (left, right) = line.split("\\s+".toRegex()).map(String::toInt)
            leftList += left
            rightList += right
        }

        // Sort both lists
        val sortedLeftList = leftList.sorted()
        val sortedRightList = rightList.sorted()

        var totalDistance = 0
        repeat(sortedLeftList.size) { index ->
            totalDistance += abs(sortedLeftList[index] - sortedRightList[index])
        }

        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        // Split each line and populate left and right lists
        input.forEach { line ->
            val (left, right) = line.split("\\s+".toRegex()).map(String::toInt)
            leftList += left
            rightList += right
        }

        var similarity = 0
        leftList.forEach { number ->
            val rightListAppearances = rightList.filter { it == number }.size
            similarity += number * rightListAppearances
        }

        return similarity
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("day_01/Day01")
    part1(input).println()
    part2(input).println()
}
