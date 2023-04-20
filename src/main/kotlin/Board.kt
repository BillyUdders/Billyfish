typealias BoardState = Array<Array<Piece?>>

class Board(fen: FENString) {
    private val moveCalc: MoveCalculator = MoveCalculator()

    val state: BoardState = Array(8) { Array(8) { null } }

    init {
        var rank = 0
        var file = 0
        for (char in fen.value) {
            if (char.isDigit()) {
                file += char.digitToInt()
            } else if (char.isLetter()) {
                state[rank][file] = Piece.fromFEN[char]
                file += 1
            } else if (char == '/') {
                rank += 1
                file = 0
            }
        }
    }

    fun makeMove(sourceInput: String, destInput: String): Move? {
        val source = Pos(sourceInput)
        val destination = Pos(destInput)

        val (x, y) = source.coordinates
        val sourcePiece = state[x][y]

        sourcePiece?.let { piece ->
            println(piece)
            val move = Move(piece, source, destination)
            val legalMoves = moveCalc.getLegalMoves(this.state)

            if (move in legalMoves) {
                val (x1, y1) = move.destPosition.coordinates
                state[x][y] = null
                state[x1][y1] = piece
                return move
            }
        }
        return null
    }

}
