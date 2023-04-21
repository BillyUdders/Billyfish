fun main(args: Array<String>) {
    val b = Board(FENString.INITIAL_BOARD_FEN)
    printState(b)
    b.makeMove(Piece.Color.WHITE, "A2", "C3")
    printState(b)
    println("\n \nPRINTING FEN:\n \n")
    println(b.generateFEN())
}

private fun printState(b: Board) {
    println("\n \nPRINTING STATE:\n \n")
    b.state.forEach {
        println(it.contentToString())
    }
}
