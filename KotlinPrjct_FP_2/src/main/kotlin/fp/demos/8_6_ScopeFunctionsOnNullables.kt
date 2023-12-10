package fp.demos

import fp.persons

fun main() {

    persons.find { it.age >= 20 }   // ?. let

    persons.find { it.age >= 30 }   // ?.apply

    persons.find { it.age >= 60 }   // ? apply

}