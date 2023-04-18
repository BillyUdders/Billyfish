fun main(args: Array<String>) {
    val b = Board(INITIAL_BOARD_FEN)
    printState(b)
    b.makeMove("A2", "E3")
    printState(b)
}

private fun printState(b: Board) {
    println("\n \nPRINTING STATE:\n \n")
    b.state.forEach {
        println(it.contentToString())
    }
}
