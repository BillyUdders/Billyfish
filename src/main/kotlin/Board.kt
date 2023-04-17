import java.util.*

data class Position(val column: Char, val row: Int) {
    private val columnMap = hashMapOf(
        'A' to 0, 'B' to 1,
        'C' to 2, 'D' to 3,
        'E' to 4, 'F' to 5,
        'G' to 6, 'H' to 7
    )
    val coordinates: Pair<Int, Int>

    init {
        val col = columnMap[column]
        val ro = row - 1
        if (col != null && ro >= 1) {
            this.coordinates = Pair(col, ro)
        } else {
            throw InvalidPropertiesFormatException("Row and column not valid: $col, $ro")
        }
    }
}

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
        val (x, y) = source.coordinates
        val (x1, y1) = destination.coordinates
        val sourcePiece = state[x][y]
        val destPiece = state[x1][y1]
        if (sourcePiece != null && destPiece == null) {
            state[x][y] = null
            state[x1][y1] = sourcePiece
        } else {
            // FIXME Make proper exception, just a placeholder for now
            throw InvalidPropertiesFormatException("Invalid move $sourcePiece -> $destPiece")
        }
    }

    fun printState() {
        for (row in state) println(row.contentToString())
    }
}
