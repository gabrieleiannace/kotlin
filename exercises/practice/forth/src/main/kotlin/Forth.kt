class Forth {
    private var STACK = intArrayOf()

    fun evaluate(vararg line: String): List<Int> {
        var evaluationStack = emptyList<Int>()
        line.map { it ->
            it.split(" ").map {
                when (it){
                    "+" -> evaluationStack = listOf(evaluationStack[0] + evaluationStack[1])
                    "-" -> evaluationStack = listOf(evaluationStack[0] - evaluationStack[1])
                    "*" -> evaluationStack = listOf(evaluationStack[0] * evaluationStack[1])
                    "/" -> evaluationStack = listOf(evaluationStack[0] / evaluationStack[1])
                    else -> evaluationStack = evaluationStack.plus(it.toInt());
                }
        } }
        return evaluationStack
    }
}
fun main(){
    val test = Forth()
    println( test.evaluate("1 2 +"))
}