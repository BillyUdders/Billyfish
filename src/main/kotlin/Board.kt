data class Position(val row: String, val column: String)

class Board(fen: String = "") {
    private val state: Array<Array<Piece?>> = Array(8) { Array(8) { null } }

    init {
        var row = 0
        var column = 0
        for (char in fen) {
            if (char.isDigit()) {
                column = column.plus(char.digitToInt())
            } else if (char == '/') {
                row = row.inc()
                column = 0
            } else if (char.isLetter()) {
                state[row][column] = Piece.FENMap[char]
                column = column.inc()
            }
        }
    }

    fun makeMove(source: Position, destination: Position) {

    }

    fun printState() {
        for (row in state) println(row.contentToString())
    }
}
