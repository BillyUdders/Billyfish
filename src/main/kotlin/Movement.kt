data class SAN(val input: String) {
    val rank: Int
    val file: Char

    init {
        if (input.length != 2) {
            throw InvalidPositionException("SAN input not 2, length: ${input.length}")
        }
        this.rank = input[1].digitToInt()
        this.file = input[0].uppercaseChar()
    }
}

data class Position(val san: SAN) {
    private val columnMap = hashMapOf(
        'A' to 0, 'B' to 1,
        'C' to 2, 'D' to 3,
        'E' to 4, 'F' to 5,
        'G' to 6, 'H' to 7
    )
    val coordinates: Pair<Int, Int>

    init {
        val rank = san.rank - 1
        val file = columnMap[san.file]
        if (file != null && rank >= 1) {
            this.coordinates = Pair(file, rank)
        } else {
            throw InvalidPositionException("$san -> $file, $rank")
        }
    }
}