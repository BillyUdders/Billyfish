val INITIAL_BOARD_FEN = FENString("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")

val MOVED_PAWNS_FEN = FENString("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R")

class FENString(val input: String) {
    init {
        val ranks = input.split('/')
        if (ranks.size != 8) {
            throw FENException("Not enough ranks in FEN String for legal board.")
        }
    }
}

