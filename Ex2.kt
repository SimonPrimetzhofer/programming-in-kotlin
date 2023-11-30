import java.io.File
import kotlin.reflect.KProperty

class Person {
    val name: String = "Tobias"
    var hobby: String? by FileStorage(name) { it }
    var age: Int? by FileStorage(name) { str -> str.toInt()}
}

class FileStorage<THIS, PROP>(val identify : String, val converter: (String) -> PROP) {
    operator fun setValue(thisRef: THIS, property: KProperty<*>, newVal: PROP?) {
        // open file to thisRef.identifyingProp-propertyname
        val path = "${identify}-${property.name}.prop"
        val file = File(path)
        if (!file.exists()) file.createNewFile()
        if (newVal != null) file.writeText(newVal.toString())
    }

    operator fun getValue(thisRef: THIS, property: KProperty<*>) : PROP? {
        val path = "${identify}-${property.name}.prop"
        val file = File(path)
        return if (!file.exists()) {
            null
        } else {
            converter(file.readText())
        }
    }
}

fun main() {
    val person = Person()
    println(person.hobby)
    person.hobby = "Gaming"
    person.age = 23 
}
