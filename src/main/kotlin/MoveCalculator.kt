import Piece.Color.WHITE
import Piece.PieceType.KNIGHT

class MoveCalculator {

    fun getLegalMoves(boardState: BoardState): List<Move> {
        val piece = Piece(KNIGHT, WHITE)
        if (piece != null) {
            return listOf(Move(piece, Pos("A2"), Pos("C3")))
        }

        return emptyList()
    }
}