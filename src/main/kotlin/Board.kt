typealias BoardState = Array<Array<Piece?>>

class Board(fen: FENString) {
    private val moveCalc: MoveCalculator = MoveCalculator()

    val state: BoardState = Array(8) { Array(8) { null } }

    init {
        var rank = 0
        var file = 0
        fen.value.forEach {
            if (it.isDigit()) {
                file += it.digitToInt()
            } else if (it.isLetter()) {
                state[rank][file] = Piece.fromFEN[it]
                file += 1
            } else if (it == '/') {
                rank += 1
                file = 0
            }
        }
    }

    fun makeMove(side: Piece.Color, sourceInput: String, destInput: String): Move? {
        val source = Pos(sourceInput)
        val destination = Pos(destInput)

        val (startX, startY) = source.coordinates
        val sourcePiece = state[startX][startY]

        return sourcePiece?.let { piece ->
            val move = Move(piece, source, destination)
            val legalMoves = moveCalc.getLegalMoves(this.generateFEN(), side)
            when (move) {
                in legalMoves -> doMove(move, startX, startY)
                else -> null
            }
        }
    }

    fun generateFEN() = FENString(
        this.state.fold(StringBuilder()) { sb, row ->
            row.forEach { piece ->
                if (piece != null) {
                    sb.append(piece.char)
                } else if (sb.last().isDigit()) {
                    val s = "${sb.dropLast(1)}${sb.last().digitToInt() + 1}"
                    sb.clear()
                    sb.append(s)
                } else {
                    sb.append("1")
                }
            }
            sb.append("/")
        }.toString().dropLast(1)
    )

    private fun doMove(move: Move, startX: Int, startY: Int): Move {
        val (destX, destY) = move.destPosition.coordinates
        state[startX][startY] = null
        state[destX][destY] = move.piece
        return move
    }

}
