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
        val regexXmas = Regex("XMAS")
        val regexSamx = Regex("SAMX")

        // Search for horizontal appearances ("XMAS" and "SAMX")
        val horizontalCount = input.sumOf { line ->
            regexXmas.findAll(line).count() + regexSamx.findAll(line).count()
        }

        // Search for vertical appearances
        val verticalCount = (0 until input[0].length).sumOf { col ->
            (0..input.size - 4).count { row ->
                input[row][col] == 'X' && input[row + 1][col] == 'M' && input[row + 2][col] == 'A' && input[row + 3][col] == 'S'
            } + (3 until input.size).count { row ->
                input[row][col] == 'X' && input[row - 1][col] == 'M' && input[row - 2][col] == 'A' && input[row - 3][col] == 'S'
            }
        }

        // Search for diagonal appearances
        val diagonalCount = (0..input.size - 4).sumOf { row ->
            (0..input[0].length - 4).count { col ->
                input[row][col] == 'X' && input[row + 1][col + 1] == 'M' && input[row + 2][col + 2] == 'A' && input[row + 3][col + 3] == 'S'
            } + (3 until input[0].length).count { col ->
                input[row][col] == 'X' && input[row + 1][col - 1] == 'M' && input[row + 2][col - 2] == 'A' && input[row + 3][col - 3] == 'S'
            }
        } + (3 until input.size).sumOf { row ->
            (0..input[0].length - 4).count { col ->
                input[row][col] == 'X' && input[row - 1][col + 1] == 'M' && input[row - 2][col + 2] == 'A' && input[row - 3][col + 3] == 'S'
            } + (3 until input[0].length).count { col ->
                input[row][col] == 'X' && input[row - 1][col - 1] == 'M' && input[row - 2][col - 2] == 'A' && input[row - 3][col - 3] == 'S'
            }
        }

        return horizontalCount + verticalCount + diagonalCount
    }

    fun part2(input: List<String>): Int {
        return (1 until input.size - 1).sumOf { row ->
            (1 until input[row].length - 1).count { col ->
                input[row][col] == 'A' &&
                        ((input[row - 1][col - 1] == 'M' && input[row + 1][col + 1] == 'S') || (input[row - 1][col - 1] == 'S' && input[row + 1][col + 1] == 'M')) &&
                        ((input[row - 1][col + 1] == 'M' && input[row + 1][col - 1] == 'S') || (input[row - 1][col + 1] == 'S' && input[row + 1][col - 1] == 'M'))
            }
        }
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/day_04/Day04.txt` file.
    val input = readInput(dir = "day_04", name = "Day04")
    part1(input).println()
    part2(input).println()
}