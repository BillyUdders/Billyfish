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
        'A' to 7, 'B' to 6,
        'C' to 5, 'D' to 4,
        'E' to 3, 'F' to 2,
        'G' to 1, 'H' to 0
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