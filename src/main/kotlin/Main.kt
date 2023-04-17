fun main(args: Array<String>) {
    val b = Board(INITIAL_BOARD_FEN)
    b.printState()

    println("\n \n MOVING \n \n")

    b.makeMove(Position('A', 2), Position('E', 3))
    b.printState()
}
