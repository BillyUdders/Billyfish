fun main(args: Array<String>) {
    val b = Board(INITIAL_BOARD_FEN)
    printState(b)

    println("\n \n MOVING \n \n")

    b.makeMove(Position("A2"), Position("E3"))
    printState(b)
}

private fun printState(b: Board) {
    for (row in b.state) println(row.contentToString())
}
