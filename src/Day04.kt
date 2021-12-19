data class BingoInput(val drawInputs: List<Int>, val boards: List<List<List<String>>>)

data class Field(val data: Int, var marked: Boolean = false) {
    override fun toString(): String {
        return if (marked) "x" else data.toString()
    }
}

class BingoBoard(private val values: List<List<Field>>) {

    override fun toString(): String {
        return values.map { it.joinToString(" ") }.joinToString(separator = "\n")
    }

    private fun checkRows(): Boolean {
        return values.any { it.all { it.marked } }
    }

    private fun checkColumns(): Boolean {
        for (column in values.first().indices) {
            var columnWin = true
            for (row in values.indices) {
                if (!values[row][column].marked) {
                    columnWin = false
                    continue
                }
            }
            if (columnWin) {
                return true
            }
        }

        return false
    }

    fun hasWon(): Boolean {
        return checkColumns() || checkRows()
    }

    fun markBoard(x: Int) {
        values.forEach { it.forEach { if (it.data == x) it.marked = true } }
    }

    fun getUnmarked(): List<Int> {
        return values.flatten().filter { !it.marked }.map { it.data }
    }

    companion object {

        fun build(values: List<List<String>>): BingoBoard {
            return BingoBoard(values.map { it.map { Field(it.toInt()) } })
        }
    }
}

fun main() {

    fun parseInput(input: List<String>): BingoInput {
        val drawInputs = input.first().split(",").asInts()
        val boardInput = input.subList(1, input.size - 1)
            .filter { it.isNotBlank() }
            .chunked(5)
            .map { it.map { it.split(" ").filter { it.isNotBlank() } } }
        return BingoInput(drawInputs, boardInput)
    }

    fun part1(input: List<String>): Int {
        val bingoInput = parseInput(input)
        val draws = bingoInput.drawInputs
        val boards = bingoInput.boards.map { BingoBoard.build(it) }

        draws.forEach { draw ->
            boards.forEach { board ->
                board.markBoard(draw)
                if (board.hasWon()) {
                    println(board)
                    return board.getUnmarked().sum() * draw
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val bingoInput = parseInput(input)
        val draws = bingoInput.drawInputs
        var boards = bingoInput.boards.map { BingoBoard.build(it) }.toMutableList()

        draws.forEach { draw ->
            boards.forEach { board ->
                board.markBoard(draw)
                if (board.hasWon()) {
                    println(board.getUnmarked().sum() * draw)
                    boards = (boards - board).toMutableList()
                }
            }
        }

        return 0
    }

    val inputPart1 = readInput("Day04")
    println(part1(inputPart1))

    val inputPart2 = readInput("Day04")
    println(part2(inputPart2))
}
