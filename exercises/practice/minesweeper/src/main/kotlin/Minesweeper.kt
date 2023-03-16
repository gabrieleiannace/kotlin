
data class MinesweeperBoard(private val inputBoard: List<String>) {

    private var mappaMine = "";

    fun withNumbers(): List<String> {
        if(inputBoard.isEmpty()) return emptyList()
        inputBoard.mapIndexed{index, value ->
            for((indice, valore) in value.withIndex()){
                if(valore == ' '){
                    mappaMine += (if(ricercaBombe(index, indice, inputBoard) != 0) ricercaBombe(index, indice, inputBoard) else " ")
                }
                else{
                    mappaMine += "*"
                }
            }
        }
        //return mappaMine.chunked(inputBoard[0]?.length)
        return if(inputBoard[0].length != 0) mappaMine.chunked(inputBoard[0].length) else listOf("")
    }
}


private fun ricercaBombe(i: Int, j: Int, input: List<String>) :Int {
    var counter:Int = 0;

    //3 precedenti
    if(i > 0){
        if(j > 0){
            if(input[i-1][j-1] == '*') counter++
        }
        if(input[i-1][j] == '*') counter++
        if (j < input[0].length - 1)
        if(input[i-1][j+1] == '*') counter++
    }
    //precedente e successivo
    if(j > 0){
        if(input[i][j-1] == '*') counter++
    }
    if(j < input[0].length - 1)
        if(input[i][j+1] == '*') counter++

    //3 successivi
    if(i < input.size - 1){
        if(j > 0){
            if(input[i+1][j-1] == '*') counter++
        }
        if(input[i+1][j] == '*') counter++
        if(j < input[0].length - 1){
            if(input[i+1][j+1] == '*') counter++
        }
    }

    return counter;
}

fun main(){
}
