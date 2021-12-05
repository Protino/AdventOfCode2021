fun main() {

    fun part1(input: List<Pair<String, Int>>): Int {
        var x = 0
        var y = 0
        input.forEach {
            when (it.first) {
                "forward" -> x += it.second
                "up" -> y -= it.second
                else -> y += it.second
            }
        }

        return x * y
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        var x = 0
        var y = 0
        var aim = 0
        input.forEach {
            when (it.first) {
                "forward" -> {
                    x += it.second
                    y += (aim * it.second)
                }
                "up" -> aim -= it.second
                else -> aim += it.second
            }
        }

        return x * y
    }

    fun List<String>.readCommands() = this.map {
        val parts = it.split(" ")
        Pair(parts.first(), parts.last().toInt())
    }

    // Cleanup input
    val inputPart1 = readInput("Day02").readCommands()
    println(part1(inputPart1))

    val inputPart2 = readInput("Day02").readCommands()
    println(part2(inputPart2))
}
