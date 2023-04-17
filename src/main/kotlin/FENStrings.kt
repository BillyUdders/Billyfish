val INITIAL_BOARD_FEN = FENString("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")

val MOVED_PAWNS_FEN = FENString("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R")

class FENString(val value: String) {
    init {
        val ranks = value.split('/')
        if (ranks.size != 8) {
            throw FENException("Not enough ranks in FEN String for legal board.")
        }
        for (rank in ranks) {
            var length = 0
            for (char in rank) {
                if (char.isDigit()) {
                    length = length.plus(char.digitToInt())
                } else if (char.isLetter()) {
                    length = length.inc()
                }
            }
            if (length != 8) {
                throw FENException("Rank length $length not legal.")
            }
        }
    }
}

