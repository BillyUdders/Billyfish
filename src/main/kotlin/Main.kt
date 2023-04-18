fun main(args: Array<String>) {
    val b = Board(INITIAL_BOARD_FEN)
    printState(b)
    b.makeMove(Position(SAN("A2")), Position(SAN("E3")))
    printState(b)
}

private fun printState(b: Board) {
    println("\n \nPRINTING STATE:\n \n")
    b.state.forEach { row ->
        println(row.contentToString())
    }
}
