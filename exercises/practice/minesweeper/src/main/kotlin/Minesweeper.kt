
data class MinesweeperBoard(private val inputBoard: List<String>) {


    fun withNumbers(): List<String> {
        inputBoard.mapIndexed{index, value ->
            println("index $index, value $value")
            for((indice, valore) in value.withIndex()){
                println("Valore --> indice $indice valore $valore")
            }

        }

        return emptyList()
    }
}

fun main(){
    val inputBoard = listOf(
        "  *  ",
        "  *  ",
        "*****",
        "  *  ",
        "  *  "
    )
    var istanza = MinesweeperBoard(inputBoard)
    println(istanza.withNumbers())
}
