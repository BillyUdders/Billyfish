enum class Color {
    WHITE, BLACK
}

enum class PieceType {
    ROOK, BISHOP, KNIGHT, KING, QUEEN, PAWN
}

data class Piece(val type: PieceType, val color: Color) {
    companion object {
        val fromFEN: Map<Char, Piece> = hashMapOf(
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
