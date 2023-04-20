import java.util.*

data class FENString(val value: String) {
    companion object {
        val INITIAL_BOARD_FEN = FENString("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")
    }

    init {
        val ranks = value.split('/')
        if (ranks.size != 8) {
            throw InputMismatchException("FEN rank length ${ranks.size} not legal.")
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
    constructor(input: String) : this(SAN(input))

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

data class Move(val piece: Piece, val sourcePosition: Position, val destPosition: Position) {
    constructor(piece: Char, sourceInput: String, destInput: String) : this(
        Piece.fromFEN[piece] ?: throw InputMismatchException("Char not valid FEN"), Pos(sourceInput), Pos(destInput)
    )
}

data class Piece(val type: PieceType, val color: Color, val char: Char) {

    enum class Color {
        WHITE, BLACK
    }

    enum class PieceType {
        ROOK, BISHOP, KNIGHT, KING, QUEEN, PAWN
    }

    companion object {
        val fromFEN: Map<Char, Piece> = hashMapOf(
            'P' to Piece(PieceType.PAWN, Color.WHITE, 'P'),
            'N' to Piece(PieceType.KNIGHT, Color.WHITE, 'N'),
            'B' to Piece(PieceType.BISHOP, Color.WHITE, 'B'),
            'R' to Piece(PieceType.ROOK, Color.WHITE, 'R'),
            'Q' to Piece(PieceType.QUEEN, Color.WHITE, 'Q'),
            'K' to Piece(PieceType.KING, Color.WHITE, 'K'),
            'p' to Piece(PieceType.PAWN, Color.BLACK, 'p'),
            'n' to Piece(PieceType.KNIGHT, Color.BLACK, 'n'),
            'b' to Piece(PieceType.BISHOP, Color.BLACK, 'b'),
            'r' to Piece(PieceType.ROOK, Color.BLACK, 'r'),
            'q' to Piece(PieceType.QUEEN, Color.BLACK, 'q'),
            'k' to Piece(PieceType.KING, Color.BLACK, 'k'),
        )
    }
}
