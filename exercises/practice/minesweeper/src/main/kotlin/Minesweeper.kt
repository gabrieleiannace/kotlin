data class MinesweeperBoard(val todo: List<String>) {

    init{
        //var bombIndex = mutableListOf<Int>()
    }
    var bombIndex = mutableListOf<Int>()

    fun withNumbers(): List<String> {
        todo.mapIndexed { index, item ->
            item.mapIndexed { r, c ->
                println("${if(c == '*') bombIndex.add(r+(item.length * index)) else " "}");
            }
        }
        return emptyList();
    }
}

fun somma(a: Int, b: Int): Int {
    return 1;
}


fun main(){
    val inputBoard = listOf(
        "  *  ",
        "  *  ",
        "*****",
        "  *  ",
        "  *  "
    )
    var istanza = MinesweeperBoard(inputBoard);
    println(istanza.withNumbers());

}
