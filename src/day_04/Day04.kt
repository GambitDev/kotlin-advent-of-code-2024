package day_04

import println
import readInput

fun main() {
    val testInput = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent().split("\n")

    fun part1(input: List<String>): Int {
        var times = 0

        // search for horizontal appearances (XMAS)
        input.forEach { line ->
            val regex = Regex("XMAS")
            times += regex.findAll(line).toList().size
        }

        // search for backward appearances (SAMX)
        input.forEach { line ->
            val regex = Regex("SAMX")
            times += regex.findAll(line).toList().size
        }

        // search for vertical appearances (going down)
        for (i in 0..input.size - 4) {
            val line = input[i]
            for (j in line.indices) {
                if (line[j] == 'X') {
                    if (input[i + 1][j] == 'M' && input[i + 2][j] == 'A' && input[i + 3][j] == 'S') {
                        times++
                    }
                }
            }
        }

        // search for vertical appearances (going up)
        for (i in input.size - 1 downTo 3) {
            val line = input[i]
            for (j in line.indices) {
                if (line[j] == 'X') {
                    if (input[i - 1][j] == 'M' && input[i - 2][j] == 'A' && input[i - 3][j] == 'S') {
                        times++
                    }
                }
            }
        }

        // diagonal
        for (i in 0..input.size - 4) {
            val line = input[i]
            for (j in 0..line.length - 3) {
                if (line[j] == 'X') {
                    if (input[i + 1][j + 1] == 'M' && input[i + 2][j + 2] == 'A' && input[i + 3][j + 3] == 'S') {
                        times++
                    }
                }
            }
        }

        // diagonal
        for (i in input.size - 1 downTo 3) {
            val line = input[i]
            for (j in line.length - 1 downTo 3) {
                if (line[j] == 'X') {
                    if (input[i - 1][j - 1] == 'M' && input[i - 2][j - 2] == 'A' && input[i - 3][j - 3] == 'S') {
                        times++
                    }
                }
            }
        }

        // diagonal
        for (i in 0..input.size - 4) {
            val line = input[i]
            for (j in line.length - 1 downTo 3) {
                if (line[j] == 'X') {
                    if (input[i + 1][j - 1] == 'M' && input[i + 2][j - 2] == 'A' && input[i + 3][j - 3] == 'S') {
                        times++
                    }
                }
            }
        }

        // diagonal
        for (i in input.size - 1 downTo 3) {
            val line = input[i]
            for (j in 0..line.length - 3) {
                if (line[j] == 'X') {
                    if (input[i - 1][j + 1] == 'M' && input[i - 2][j + 2] == 'A' && input[i - 3][j + 3] == 'S') {
                        times++
                    }
                }
            }
        }

        return times
    }

    fun part2(input: List<String>): Int {
        var times = 0
        for (i in 1 until input.size - 1) {
            for (j in 1 until input[i].length - 1) {
                if (input[i][j] == 'A') {
                    if (input[i - 1][j - 1] == 'M' && input[i + 1][j + 1] == 'S' || input[i - 1][j - 1] == 'S' && input[i + 1][j + 1] == 'M') {
                        if (input[i - 1][j + 1] == 'M' && input[i + 1][j - 1] == 'S' || input[i - 1][j + 1] == 'S' && input[i + 1][j - 1] == 'M') {
                            times++
                        }
                    }
                }
            }
        }

        return times
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/day_04/Day04.txt` file.
    val input = readInput(dir = "day_04", name = "Day04")
    part1(input).println()
    part2(input).println()
}