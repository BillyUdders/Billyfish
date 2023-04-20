import Piece.Color.BLACK
import Piece.PieceType.BISHOP

class LegalMoveCalculator {

    fun getLegalMoves(boardState: BoardState, source: Position, destination: Position): List<Pair<Position, Position>> {
        if (Piece(BISHOP, BLACK) != null) {
            return listOf(source to destination)
        }

        return emptyList()
    }
}