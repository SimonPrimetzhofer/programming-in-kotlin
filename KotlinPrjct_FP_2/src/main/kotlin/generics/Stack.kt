package generics

interface Stack<T> {
    fun isEmpty(): Boolean
    fun push(t: T): Unit
    fun top(): T
    fun pop(): T
}

class LinkedStack<T>(vararg elems : T) : Stack<T> {

    private data class Node<E>(val value: E, val next: Node<E>?)

    inner class Iterator : kotlin.collections.Iterator<T> {
        private var curr: Node<T>? = first
        override fun hasNext(): Boolean { return first != null}

        override fun next(): T {
            val v = curr?.value?: throw java.util.NoSuchElementException()
            curr = curr?.next
            return v
        }
    }

    private var first : Node<T>? = buildNodes(elems)

    override fun isEmpty() : Boolean = first == null

    override fun push(t: T) : Unit {
        val node = Node<T>(t, first)
        first = node
    }

    override fun top() : T = if (first == null) throw NoSuchElementException() else first!!.value

    override fun pop() : T {
        if (first == null) throw NoSuchElementException()
        else {
            val t = first!!.value
            first = first!!.next
            return t
        }
    }

    private fun buildNodes(elems: Array<out T>) : Node<T>? {
        var node : Node<T>? = null
        for (e in elems) {
            node = Node(e, node)
        }
        return node
    }

    fun iterator() = Iterator()
    override fun toString(): String {
        return "Stack(${mkStringFromNodes()})"
    }

    private fun mkStringFromNodes() : String {
        var s = ""
        var n : Node<T>? = first
        while (n != null) {
            s = s + n.value.toString() + ", "
            n = n.next
        }
        return s.substring(0, s.length - 2)
    }
}

fun <T> stackOf(vararg elems: T) : Stack<T> {
    return LinkedStack<T>(*elems)
}

fun <T> Stack<T>.pushAll(vararg elems: T) {
    for (e in elems) {
        this.push(e)
    }
}

fun main(args: Array<String>) {

    val stackOfInts: Stack<Int> = LinkedStack(1)
    val stackOfBooleans: Stack<Boolean> = LinkedStack(true)

    val stackOfStrings = stackOf("Hello", "Kotlin")
    val stackOfInts2 = stackOf(1, 2, 3)
    val stackOfAny = stackOf(1, "Hello")

    println("Hello strings! " + stackOfStrings)
    println("Hello ints! " + stackOfInts)
    println("Hello ints! " + stackOfInts2)
    println("Hello any! " + stackOfAny)
    println("Hello any! " + stackOfBooleans)

    stackOfStrings.pushAll("and", "Java")

    println("Hello strings! " + stackOfStrings)

    val stackOfInts3 = stackOf(1, 2)
    stackOfInts3.pushAll(3, 4)

}