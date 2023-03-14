data class MinesweeperBoard(val todo: List<String>) {

    // TODO: Implement proper constructor
//    init {
//        println(todo);
//    }


    fun withNumbers(): List<String> {

        return emptyList()
    }
}

fun main(){
    val strings = listOf("·*·*·", "··*··", "··*··", "·····");
    var arr = emptyArray<Int>();

    strings.mapIndexed{row, col ->
        col.mapIndexed{r, c ->
            if (c == '*') {
                arr = arr.plus(r+(row * col.length));
            }
        }
    }
    println(arr.contentToString());

    val flat = strings.flatMap { it.toList() }
    println(flat)

    for ((index,value) in flat.withIndex()){
        if(value == '·'){
            val result = arr.find{ it > 5 }
            println(result)
        }
    }
}
