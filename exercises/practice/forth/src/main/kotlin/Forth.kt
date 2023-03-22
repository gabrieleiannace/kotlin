class Forth {

    private var isWordDefinition: Boolean = false
    private var definedWordBuffer:String = ""
    private var definedWord: String = ""

    fun evaluate(vararg line: String): List<Int> {
        var evaluationStack = emptyList<Int>().toMutableList()
        line.mapIndexed { indice, it ->
            it.split(" ").mapIndexed { index, it ->
                when (it){
                    definedWord -> return (evaluate(line[indice].replace(definedWord, definedWordBuffer)))
                    "+" ->
                        if(!isWordDefinition) {
                            if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                            else if (evaluationStack.size == 1) throw CustomException("only one value on the stack")
                            else evaluationStack = listOf(evaluationStack[0] + evaluationStack[1]).toMutableList()
                        }else{
                            definedWordBuffer = definedWordBuffer.plus("$it ")
                        }

                    "-" ->
                        if(!isWordDefinition) {
                            if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                            else if (evaluationStack.size == 1) throw CustomException("only one value on the stack")
                            else evaluationStack = listOf(evaluationStack[0] - evaluationStack[1]).toMutableList()
                        }else{
                            definedWordBuffer = definedWordBuffer.plus("$it ")
                        }

                    "*" ->
                        if(!isWordDefinition) {
                            if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                            else if (evaluationStack.size == 1) throw CustomException("only one value on the stack")
                            else evaluationStack = listOf(evaluationStack[0] * evaluationStack[1]).toMutableList()
                        }else{
                            definedWordBuffer = definedWordBuffer.plus("$it ")
                        }

                    "/" ->
                        if(!isWordDefinition) {
                            if (evaluationStack.isEmpty() && !isWordDefinition) throw CustomException("empty stack")
                            else if (evaluationStack.size == 1) throw CustomException("only one value on the stack")
                            else if (evaluationStack[1] == 0) throw CustomException("divide by zero")
                            else evaluationStack = listOf(evaluationStack[0] / evaluationStack[1]).toMutableList()
                        } else{
                            definedWordBuffer = definedWordBuffer.plus("$it ")
                        }


                    "dup" ->
                        if(!isWordDefinition) {
                            if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                            else evaluationStack =
                                evaluationStack.plus(evaluationStack[evaluationStack.size - 1]).toMutableList()
                        } else {
                            definedWordBuffer = definedWordBuffer.plus("$it ")
                        }

                    "drop" ->
                        if(!isWordDefinition) {
                            if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                            else evaluationStack.removeLastOrNull()
                        }else{
                            definedWordBuffer = definedWordBuffer.plus("$it ")
                        }

                    "swap" ->
                        if(!isWordDefinition) {
                            if (evaluationStack.isEmpty() && !isWordDefinition) throw CustomException("empty stack")
                            else if (evaluationStack.size == 1) throw CustomException("only one value on the stack")
                            else {
                                val tmp = evaluationStack[evaluationStack.size - 2]
                                evaluationStack[evaluationStack.size - 2] = evaluationStack[evaluationStack.size - 1]
                                evaluationStack[evaluationStack.size - 1] = tmp
                            }
                        }else{
                            definedWordBuffer = definedWordBuffer.plus("$it ")
                        }
                    "over" ->
                        if(!isWordDefinition) {
                            if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                            else if (evaluationStack.size == 1) throw CustomException("only one value on the stack")
                            else {
                                evaluationStack =
                                    evaluationStack.plus(evaluationStack[evaluationStack.size - 2]).toMutableList()
                            }
                        }else{
                            definedWordBuffer = definedWordBuffer.plus("$it ")
                        }
                    ":" -> isWordDefinition = true
                    ";" -> {
                        isWordDefinition = false
                        definedWordBuffer = definedWordBuffer.dropLast(1)
                    }
                    else ->
                        if(!isWordDefinition) evaluationStack = evaluationStack.plus(it.toInt()).toMutableList()
                        else {
                            if(definedWord.isNotEmpty()){
                                definedWordBuffer = definedWordBuffer.plus(("$it "))
                            }else{
                                definedWord = it
                            }
                        }

                }
        } }
        return evaluationStack
    }



}

class CustomException(message: String) : Exception(message)

fun main(){
    var lista = listOf<String>(": foo dup ;", ": foo dup dup ;", "1 foo")
    var myMap = emptyMap<String, String>()

    lista.mapIndexed{index, cella ->
        if(lista[index][0] == ':' && lista[index][lista[index].length - 1] == ';') {
            println(lista[index].split(" ")[1])
            val command = lista[index].split(" ").subList(2, lista[index].split(" ").size - 1).joinToString(" ")
            myMap = myMap.plus(lista[index].split(" ")[1] to command)
            println(myMap)
        }
    }
}
