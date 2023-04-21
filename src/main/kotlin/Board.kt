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

        sourcePiece?.let { piece ->
            val move = Move(piece, source, destination)
            val legalMoves = moveCalc.getLegalMoves(this.generateFEN(), side)
            if (move in legalMoves) {
                val (destX, destY) = move.destPosition.coordinates

                state[startX][startY] = null
                state[destX][destY] = move.piece

                return move
            }
        }

        return null
    }

    fun generateFEN() = FENString(
        this.state.fold(StringBuilder()) { acc, row ->
            row.forEach { piece ->
                if (piece != null) {
                    acc.append(piece.char)
                } else if (acc.last().isDigit()) {
                    val s = "${acc.dropLast(1)}${acc.last().digitToInt() + 1}"
                    acc.clear()
                    acc.append(s)
                } else {
                    acc.append("1")
                }
            }
            acc.append("/")
        }.toString().dropLast(1)
    )

}
