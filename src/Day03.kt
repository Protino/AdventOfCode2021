import kotlin.math.abs

fun List<String>.binaryToDecimal() = this.joinToString("").toInt(2)

fun main() {

    fun part1(input: List<String>): Int {

        var oneSums = input.first().map { 0 } // Total number of ones in each bit position

        input.map {
            it.split("").mapNotNull { it.toIntOrNull() }
        }.forEach {
            oneSums = it.zip(oneSums) { a, b -> a + b }
        }

        val zeroSums = oneSums.map { abs(it - input.first().length) }

        val gamma = zeroSums.zip(oneSums) { a, b -> if (a > b) "0" else "1" }
        val epsilon = gamma.map { if (it == "0") "1" else "0" } // flipped

        return gamma.binaryToDecimal() * epsilon.binaryToDecimal()
    }

    fun computeComponent(input: List<String>, bitPosition: Int = 0, criteria: (zeroes: Int, ones: Int) -> Char): Int {
        if (input.size == 1) {
            return input.binaryToDecimal()
        }

        var zeroes = 0
        var ones = 0
        input.forEach {
            if (it[bitPosition] == '0') {
                zeroes += 1
            } else {
                ones += 1
            }
        }

        return computeComponent(
            input.filter { it[bitPosition] == criteria(zeroes, ones) },
            (bitPosition + 1) % input.first().length,
            criteria
        )
    }

    fun part2(input: List<String>): Int {
        val oxygenRating = computeComponent(input) { zeroes, ones -> if (zeroes > ones) '0' else '1' }

        val co2Rating = computeComponent(input) { zeroes, ones -> if (zeroes > ones) '1' else '0' }

        return oxygenRating * co2Rating
    }

    val inputPart1 = readInput("Day03")
    println(part1(inputPart1))

    val inputPart2 = readInput("Day03")
    println(part2(inputPart2))
}
