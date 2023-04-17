fun main(args: Array<String>) {
    val b = Board("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R")
    b.printState()

    println("\n \n MOVING \n \n")

    b.makeMove(Position('A', 2), Position('E', 3))
    b.printState()
}
