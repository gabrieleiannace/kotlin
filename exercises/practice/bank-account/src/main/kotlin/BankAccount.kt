class BankAccount {

    private var isOpen: Boolean = true
    var balance : Long = 0
        get() { check( isOpen ); return field }
        set( value ) { field = value }

    @Synchronized fun adjustBalance(amount: Long){
        balance += amount
    }

    fun close() {
        isOpen = false
    }
}
fun main(){

}