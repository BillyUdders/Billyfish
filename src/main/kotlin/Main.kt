fun main(args: Array<String>) {
    val b = Board(INITIAL_BOARD_FEN)
    b.printState()

    println("\n \n MOVING \n \n")

    b.makeMove(Position("A2"), Position("E3"))
    b.printState()
}
