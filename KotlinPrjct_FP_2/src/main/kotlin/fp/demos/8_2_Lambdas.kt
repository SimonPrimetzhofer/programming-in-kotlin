package fp.demos

import fp.Person
import fp.persons

fun main() {

    var ages = persons.map({ p: Person -> p.age})

    ages = persons.map({ p -> p.age})

    ages = persons.map ({ it.age})

    ages = persons.map {
        it.age
    }
    
    persons.map { it.age}

    persons.forEach {
        println(it)
    }

    val sumAges = persons.fold(0) {
        ages, p -> ages + p.age
    }
    val count = persons.fold(0) {
        n, p -> n + 1
    }
    val names = persons.fold ("") {
        ns, p -> "$ns ${p.firstName}"
    }

    with(persons.get(0)) {
        println(this)
    }

}

fun printHello(person: Person) = println("Hello ${person.firstName}")

fun countAdults(persons : List<Person>) : Int {

    var nAdults = 0
    //val action : (Person) -> Unit = { person: Person -> if (person.age >= 18) nAdults++ }
    persons.forEach { person: Person -> if (person.age >= 18) nAdults++ }
    return nAdults
}