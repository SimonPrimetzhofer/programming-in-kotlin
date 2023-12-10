package generics

open abstract class Person(private val name: String) : Comparable<Person> {
    override fun compareTo(other: Person): Int = this.name.compareTo(other.name)
}

data class Student(val name: String) : Person(name)
data class Professor(val name: String) : Person(name)

fun printElems(list : MutableList<Any>) {
    for (e in list) {
        println(e)
    }
}

fun <T> copyTo1(from: MutableList<out T>, to: MutableList<T>) {
    for(t in  from) {
        to.add(t);
    }
}
fun <T> copyTo2(from: MutableList<T>, to: MutableList<in T>) {
    for(f in  from) {
        to.add(f);
    }
}
fun <T, F : T> copyTo3(from: MutableList<F>, to: MutableList <T>) {
    for(f in  from) {
        to.add(f);
    }
}
fun <T> copyTo4(from: List<T>, to: MutableList<T>) {
    for(t in  from) {
        to.add(t);
    }
}

fun <T: Comparable<T>> max(x: T, y: T) : T = if (x > y) x else y

fun main() {

    with ("a") {
        println(this)
    }

    val mlist = mutableListOf<String>("Hallo", "Kotlin")
    // printElems(mlist)

    val students : MutableList<Student> = mutableListOf(Student("Joe"))

    val persons : MutableList<out Person> = students
    val person : Person = persons.get(0)
    println(person)
    //persons.add(Professor("Maier"))

    val persons2 : MutableList<Person> = mutableListOf()
    val students2 : MutableList<in Student> = persons2
    students2.add(Student("Joe"))
    //val s : Student = students2.get(0)
    println(persons2)

    val student1 = Student("Joe")
    val student2 = Student("Jeff")

    val first = max(student1, student2)
    println(first)

}