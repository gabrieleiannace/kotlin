data class MinesweeperBoard(val todo: Nothing) {


    private var bombIndex = mutableListOf<Int>()

    fun withNumbers(): List<String> {
        todo.mapIndexed { index, item ->
            item.mapIndexed { r, c ->
                "${if(c == '*') bombIndex.add(r+(item.length * index)) else " "}"
            }
        }
        return calcolaAdiacenze(todo, bombIndex)
    }
}

private fun calcolaAdiacenze(strings: List<String>, bombIndex: MutableList<Int>): List<String> {
    var result = ""
    val joinedString = strings.joinToString(separator = "")

    for((indice, cella) in joinedString.withIndex()){
        if(cella == ' '){
            val numeroBombe = bombIndex.filter {
                    it == (indice - 1) || it == (indice + 1) ||
                            it == (indice - strings[0].length - 1) || it == (indice - strings[0].length) || it == (indice - strings[0].length + 1) ||
                            it == (indice + strings[0].length - 1) || it == (indice + strings[0].length) || it == (indice + strings[0].length + 1)
                }.size
            if (numeroBombe != 0) result += numeroBombe else result += " "
        }
        else{
            result += cella
        }
    }

    return result.chunked(5)
}


fun main(){
//    val inputBoard = listOf(
//        " "
//    )
//    var istanza = MinesweeperBoard(inputBoard)
//    println(istanza.withNumbers())

}
