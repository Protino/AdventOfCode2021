fun main() {

    fun part1(input: List<Int>): Int {
        val mean = input.average() / input.size
        val fuel = 0
        return fuel
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val inputPart1 = readInput("Day03").asInts()
    println(part1(inputPart1))

    val inputPart2 = readInput("Day03")
    println(part2(inputPart2))
}
