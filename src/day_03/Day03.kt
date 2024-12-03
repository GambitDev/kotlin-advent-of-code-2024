package day_03

import println
import readInput

fun main() {
    val testInput = """
        xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    """.trimIndent().split("\n")

    fun part1(input: List<String>): Int {
        val regex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)")
        val matches = extractMatches(input, regex)
        return calculateProduct(matches)
    }

    fun part2(input: List<String>): Int {
        val regex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)")
        var collecting = true
        val filteredMatches = mutableListOf<String>()

        val matches = extractMatches(input, regex)
        matches.forEach { match ->
            when (match) {
                "don't()" -> collecting = false
                "do()" -> collecting = true
                else -> if (collecting) filteredMatches.add(match)
            }
        }

        return calculateProduct(filteredMatches)
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/day_03/Day03.txt` file.
    val input = readInput(dir = "day_03", name = "Day03")
    part1(input).println()
    part2(input).println()
}

private fun extractMatches(input: List<String>, regex: Regex): List<String> {
    return input.flatMap { line ->
        regex.findAll(line).map { it.value }.toList()
    }
}

private fun calculateProduct(matches: List<String>): Int {
    return matches.sumOf { mulCommand ->
        val digits = mulCommand.drop(4).dropLast(1).split(",").map { it.toInt() }
        digits.reduce { acc, i -> acc * i }
    }
}
