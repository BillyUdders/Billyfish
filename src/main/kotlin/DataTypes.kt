import java.util.InputMismatchException

data class FENString(val value: String) {
    companion object {
        val INITIAL_BOARD_FEN = FENString("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")
    }

    init {
        val ranks = value.split('/')
        if (ranks.size != 8) {
            throw InputMismatchException("FEN not enough ranks in FEN String for legal board.")
        }
        for (file in ranks) {
            var length = 0
            for (char in file) {
                if (char.isDigit()) {
                    length += char.digitToInt()
                } else if (char.isLetter()) {
                    length += 1
                }
            }
            if (length != 8) {
                throw InputMismatchException("FEN file length $length not legal.")
            }
        }
    }
}

data class SAN(val input: String) {
    val rank: Int
    val file: Char

    init {
        if (input.length != 2) {
            throw InputMismatchException("SAN length not 2, length: ${input.length}")
        }
        this.rank = input[1].digitToInt()
        this.file = input[0].uppercaseChar()
    }
}

typealias Pos = Position

data class Position(val san: SAN) {

    // Secondary constructor helps keep typing down by wrapping SAN creation,
    // letting user just pass a string but we still make a SAN to parse input string.
    constructor(input: String): this(SAN(input))

    private val columnMap = hashMapOf(
        'A' to 7, 'B' to 6,
        'C' to 5, 'D' to 4,
        'E' to 3, 'F' to 2,
        'G' to 1, 'H' to 0
    )

    val coordinates: Pair<Int, Int>

    init {
        val rank = san.rank - 1
        val file = columnMap[san.file]
        if (file != null && rank >= 1) {
            this.coordinates = Pair(file, rank)
        } else {
            throw InputMismatchException("Position invalid: $san -> $file, $rank")
        }
    }
}

data class Piece(val type: PieceType, val color: Color) {

    enum class Color {
        WHITE, BLACK
    }

    enum class PieceType {
        ROOK, BISHOP, KNIGHT, KING, QUEEN, PAWN
    }

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
