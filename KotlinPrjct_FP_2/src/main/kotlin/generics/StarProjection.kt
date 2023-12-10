package generics

fun main() {

    val students : MutableList<Student> = mutableListOf(Student("Joe"))

    val anyElems : MutableList<*> = students

    val any: Any? = anyElems.get(0)
    //anyElems.add(Professor("Horst", "Sieber", 62))

    val anyElems2 : MutableList<out Any?> = students
    val any2: Any? = anyElems2.get(0)
    //anyElems2.add(Professor("Horst", "Sieber", 62))

    val anyElems3 : MutableList<in Nothing> = students

    val any3 : Any? = anyElems3.get(0)
    //anyElems3.add(Professor("Horst", "Sieber", 62))

    val someList : List<*> = students

}