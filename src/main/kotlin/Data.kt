enum class Color {
    WHITE, BLACK
}

enum class PieceType {
    ROOK, BISHOP, KNIGHT, KING, QUEEN, PAWN
}

data class Position(val row: String, val column: String)

data class Piece(val type: PieceType, val color: Color) {
    companion object {
        val FENMap: Map<Char, Piece> = hashMapOf(
            'P' to Piece(PieceType.PAWN, Color.WHITE),
            'N' to Piece(PieceType.KNIGHT, Color.WHITE),
            'B' to Piece(PieceType.BISHOP, Color.WHITE),
            'R' to Piece(PieceType.ROOK, Color.WHITE),
            'Q' to Piece(PieceType.QUEEN, Color.WHITE),
            'K' to Piece(PieceType.KING, Color.WHITE),
            'p' to Piece(PieceType.PAWN, Color.BLACK),
            'n' to Piece(PieceType.KNIGHT, Color.BLACK),
            'b' to Piece(PieceType.BISHOP, Color.BLACK),
            'r' to Piece(PieceType.ROOK, Color.BLACK),
            'q' to Piece(PieceType.QUEEN, Color.BLACK),
            'k' to Piece(PieceType.KING, Color.BLACK),
        )
    }
}

class Board(fen: String = "") {
    private val coords: Array<Array<String>> = Array(8) { outer ->
        Array(8) {
            val columnNames = arrayOf("A", "B", "C", "D", "E", "F", "G", "H")
            "${columnNames[it]}:${outer + 1}"
        }
    }
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

    fun printCoords() {
        for (row in coords) println(row.contentToString())
    }

    fun printState() {
        for (row in state) println(row.contentToString())
    }
}
