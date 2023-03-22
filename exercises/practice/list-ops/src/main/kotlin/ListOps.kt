fun <T> List<T>.customAppend(list: List<T>): List<T> {
    val result = mutableListOf<T>()
    result.addAll(this)
    result.addAll(list)
    return result
}
fun <T> flatten(list: List<*>): List<T> {
    val result = mutableListOf<T>()
    for (item in list) {
        if (item is List<*>) {
            result.addAll(flatten(item))
        } else {
            result.add(item as T)
        }
    }
    return result
}
fun List<Any>.customConcat(): List<Any> {
    val result = mutableListOf<Any>()
    result.addAll(flatten(this))
    return result
}
fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (it in this){
        if (predicate(it)){
            result.add(it)
        }
    }
    return result
}
val List<Any>.customSize: Int get() = this.size
fun <T, U> List<T>.customMap(transform: (T) -> U): List<U> {
    val result = mutableListOf<U>()
    for (it in this){
        result += transform(it)
    }
    return result
}
fun <T, U> List<T>.customFoldLeft(initial: U, f: (U, T) -> U): U {
    var result: U = initial
    for (it in this){
        result = f(result, it)
    }
    return result
}
fun <T, U> List<T>.customFoldRight(initial: U, f: (T, U) -> U): U {
    var result: U = initial
    var i: Int = this.size-1
    while (i >= 0){
        result = f(this[i], result)
        i--
    }
    return result
}
fun <T> List<T>.customReverse(): List<T> {
    var result = mutableListOf<T>()
    var i: Int = this.size-1
    while (i >= 0){
        result.add(this[i])
        i--
    }
    return result
}
