class Forth {

    fun evaluate(vararg line: String): List<Int> {
        var evaluationStack = emptyList<Int>()
        line.map { it ->
            it.split(" ").map {
                when (it){
                    "+" ->
                        if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                        else if(evaluationStack.size == 1) throw CustomException("only one value on the stack")
                        else evaluationStack = listOf(evaluationStack[0] + evaluationStack[1])

                    "-" ->
                        if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                        else if(evaluationStack.size == 1) throw CustomException("only one value on the stack")
                        else evaluationStack = listOf(evaluationStack[0] - evaluationStack[1])

                    "*" ->
                        if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                        else if(evaluationStack.size == 1) throw CustomException("only one value on the stack")
                        else evaluationStack = listOf(evaluationStack[0] * evaluationStack[1])

                    "/" ->
                        if (evaluationStack.isEmpty()) throw CustomException("empty stack")
                        else if(evaluationStack.size == 1) throw CustomException("only one value on the stack")
                        else if(evaluationStack[1] == 0) throw CustomException("divide by zero")
                        else evaluationStack = listOf(evaluationStack[0] / evaluationStack[1])

                    else -> evaluationStack = evaluationStack.plus(it.toInt())
                }
        } }
        return evaluationStack
    }
}

class CustomException(message: String) : Exception(message)


fun main(){
    val test = Forth()
    println( test.evaluate("1 2 +"))
}