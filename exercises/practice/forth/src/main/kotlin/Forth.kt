import java.util.*
class Forth {
    private val userDefOperations = mutableMapOf<String, List<String>>()
    private fun operator(word: String, vararg args: Int, i: Int= 0): Int {
        var result = 0
        when (word){
            "+" -> args.map { arg ->  result += arg}
            "-" -> result = args[0] - args[1]
            "*" -> result = args[0] * args[1]
            "/" -> result = args[0] / args[1]
            "dup" -> result = args[0]
        }
        return result
    }
    private fun checkError(i: Int, stack: List<String>){
        when(stack[i]){
            "+","-","*","/","swap","over" ->
                if (i == 0){
                    throw Exception("empty stack")
                }else if (i == 1){
                    throw Exception("only one value on the stack")
                }else if(stack[i] == "/" && stack[i-1].toInt() == 0){
                    throw Exception("divide by zero")
                }
            "dup","drop" ->
                if (i == 0){
                    throw Exception("empty stack")
                }
            ":" ->
                if (i == stack.size-1){
                    throw Exception("undefined operation")
                }else if (stack[i+1].toIntOrNull() != null){
                    throw Exception("illegal operation")
                }
            else ->
                if (userDefOperations.getOrDefault(stack[i], null) !is List){
                    throw Exception("undefined operation")
                }
        }
    }
    fun evaluate(vararg lines: String): List<Int> {
        val stack = mutableListOf<String>()
        val finalStack = mutableListOf<Int>()
        var i = 0
        lines.map { line ->
            val tempList = line.split(" ")
            stack += tempList
        }
        stack.replaceAll { if (it.any { char -> char.isUpperCase() }) it.toLowerCase() else it }
        while (i < stack.size){
            if (userDefOperations.containsKey(stack[i])){ // Replace user modified words
                val newWords = userDefOperations.getOrDefault(stack[i], listOf())
                stack.addAll(i, newWords)
                stack[i+newWords.size] = ""
                stack.removeIf { it == "" }
            }
            println(stack)
            println("i = $i")
            if (stack[i].toIntOrNull() == null){
                when(stack[i]){
                    "+","-","*","/" ->
                        try {
                            checkError(i, stack)
                            val a = stack[i-2]
                            val b = stack[i-1]
                            val c = operator(stack[i], a.toInt(), b.toInt()).toString()
                            stack[i-2] = ""
                            stack[i-1] = c
                            stack[i] = ""
                            stack.removeIf { it == "" }
                            println(stack)
                            i = 0
                        }catch (err: Exception){
                            println(err.message)
                            throw err
                        }
                    "dup" ->
                        try {
                            checkError(i, stack)
                            val a = stack[i-1]
                            val b = operator(stack[i], a.toInt()).toString()
                            stack[i] = b
                            i = 0
                        }catch (err: Exception){
                            println(err.message)
                            throw err
                        }
                    "drop" ->
                        try {
                            checkError(i, stack)
                            stack[i] = ""
                            stack[i-1] = ""
                            stack.removeIf { it == "" }
                            i = 0
                        }catch (err: Exception){
                            println(err.message)
                            throw err
                        }
                    "swap" ->
                        try {
                            checkError(i, stack)
                            val a = stack[i-2]
                            val b = stack[i-1]
                            val c = a
                            stack[i-2] = b
                            stack[i-1] = c
                            stack[i] = ""
                            stack.removeIf { it == "" }
                            i = 0
                        }catch (err: Exception){
                            println(err.message)
                            throw err
                        }
                    "over" ->
                        try {
                            checkError(i, stack)
                            stack[i] = stack[i-2]
                            stack.removeIf { it == "" }
                            i = 0
                        }catch (err: Exception){
                            println(err.message)
                            throw err
                        }
                    ":" ->
                        try {
                            checkError(i, stack)
                            val tempStack = mutableListOf<String>()
                            var j = i+2
                            while (stack[j] != ";"){
                                if (userDefOperations.containsKey(stack[j])){
                                    val newWords = userDefOperations.getOrDefault(stack[j], listOf())
                                    stack.addAll(j, newWords)
                                    stack[j+newWords.size] = ""
                                    stack.removeIf { it == "" }
                                    j = i+2
                                }else{
                                    j++
                                }
                            }
                            j = i+2
                            while (stack[j] != ";"){
                                tempStack += stack[j]
                                stack[j] = ""
                                j++
                            }
                            stack[j] = ""
                            userDefOperations[stack[i+1]] = tempStack
                            println("User Defined Operations = $userDefOperations")
                            stack[i+1] = ""
                            stack[i] = ""
                            stack.removeIf { it == "" }
                            i = 0
                        }catch (err: Exception){
                            println(err.message)
                            throw err
                        }
                    else ->
                        try {
                            checkError(i, stack)
                            val newWords = userDefOperations.getOrDefault(stack[i], listOf())
                            stack[i] = ""
                            stack.addAll(i, newWords)
                            stack.removeIf { it == "" }
                            i = 0
                        }catch (err: Exception){
                            println(err.message)
                            throw err
                        }
                }
            }else{
                i++
            }
        }
        finalStack += stack.map { it.toInt() }
        return finalStack
    }
}
fun main() {
    val l1 = mutableListOf<Int>(1,2,3)
    val l2 = mutableListOf<Int>(11,12,13)
    l1.addAll(1, l2)
    println(l1)
}