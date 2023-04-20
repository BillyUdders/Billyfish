typealias BoardState = Array<Array<Piece?>>

class Board(fen: FENString) {
    private val legalCalc: LegalMoveCalculator = LegalMoveCalculator()

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

    fun makeMove(sourceInput: String, destInput: String): Pair<Pos, Pos>? {
        val source = Pos(sourceInput)
        val destination = Pos(destInput)
        val legalMoves = legalCalc.getLegalMoves(this.state, source, destination)

        val (x, y) = source.coordinates
        val (x1, y1) = destination.coordinates
        val sourcePiece = state[x][y]
        val destPiece = state[x1][y1]
        val move = source to destination
        if (sourcePiece != null && destPiece == null && move in legalMoves) {
            state[x][y] = null
            state[x1][y1] = sourcePiece
            return move
        }
        return null
    }

}
