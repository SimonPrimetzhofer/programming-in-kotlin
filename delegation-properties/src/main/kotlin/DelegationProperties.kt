import java.io.File
import kotlin.reflect.KProperty

class Person(private val name: String) {
    var hobby: String? by FileStorage(
        { it.name },
        { property -> property },
        { str -> str }
    )
    var age: Int? by FileStorage(
        { it.name },
        { property -> property.toString() },
        { str -> str.toInt() }
    )

    override fun toString(): String {
        return "$name $age $hobby"
    }
}

class FileStorage<THIS, PROP>(
    val identifierFn: (obj: THIS) -> String,
    val propToTextFn: (property: PROP) -> String,
    val textToPropFn: (text: String) -> PROP
) {
    operator fun setValue(thisRef: THIS, property: KProperty<*>, newVal: PROP?) {
        val path = "${identifierFn(thisRef)}_${property.name}.prop"

        val file = File(path)

        if (!file.exists()) {
            file.createNewFile()
        }

        if (newVal == null) {
            file.delete()
        } else {
            file.writeText(propToTextFn(newVal))
        }
    }

    operator fun getValue(thisRef: THIS, property: KProperty<*>): PROP? {
        val path = "${identifierFn(thisRef)}_${property.name}.prop"

        val file = File(path)

        return if (!file.exists()) {
            null
        } else {
            textToPropFn(file.readText())
        }
    }
}

fun main() {
    // Persons have a name (identifying property),
    // a hobby (delegated to a StringFileStorage)
    // and an age (delegated to an IntFileStorage)
    // Also, toString is overridden
    // hobby and age are null by default
    val markus = Person("Markus")
    val daniel = Person("Daniel")
    val sandra = Person("Sandra")

    // If everything is implemented correct, this prints null hobbies
    // and null ages on the first run,
    // but markus should start completely initialized on a second program run
    println(markus)
    println(daniel)
    println(sandra)

    // Set all properties, this writes to six files (and creates them if not existing)
    markus.age = 29
    daniel.age = 24
    sandra.age = 27
    markus.hobby = "Board gaming"
    daniel.hobby = "Geocaching"
    sandra.hobby = "Breakdance"
    println(markus)
    println(daniel)
    println(sandra)

    // Deletes the two property files
    daniel.age = null
    sandra.hobby = null

    // Two properties show as null (daniel's age and sandra's hobby)
    println(markus)
    println(daniel)
    println(sandra)
}
