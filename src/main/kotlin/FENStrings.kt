class FENString(val input: String) {
    init {
        val ranks = input.split('/')
        if (ranks.size != 8) {
            throw FENException("Not enough ranks in FEN String for legal board.")
        }
    }
}