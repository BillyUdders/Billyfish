class MoveCalculator {

    fun getLegalMoves(boardState: BoardState): List<Move> {
        val piece = Piece.fromFEN['N']
        piece?.let {
            return listOf(Move(piece.char, "A2", "C3"))
        } ?: run {
            return emptyList()
        }
    }
}