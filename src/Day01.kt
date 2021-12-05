fun main() {

    // cc: AntonArhipov for windowed function
    fun part1(input: List<Int>): Int {
        return input.windowed(2).count { (a, b) -> a < b }
    }

    fun part2(input: List<Int>): Int {
        return input.windowed(4).count { it.first() < it.last() }
    }

    val inputPart1 = readInput("Day01").asInts()
    println(part1(inputPart1))

    val inputPart2 = readInput("Day01").asInts()
    println(part2(inputPart2))
}
