package fp.demos

internal val nl = "\n"
internal val nlnl = "\n\n"

interface Renderable {
    fun render(b: StringBuilder): Unit
    fun render(): String {
        val b = StringBuilder()
        render(b)
        return b.toString()
    }
}

interface Element : Renderable

abstract class ElementContainer : Renderable {
    
    override fun render(b: StringBuilder)  {
    }

}

class Doc : ElementContainer() {
}

class Section() : ElementContainer(), Element {
}

class Paragraph : Element {

    override fun render(b: StringBuilder)  {
    }

}

fun main() {
    /*

    val doc =
        document {
            title = "Programming in Kotlin"
            authors = "Markus, Herbert, Alex"
            affiliation = "Institute for System Software"
            section("Basics") {
                paragraph {
                    line("Classes in Kotlin can be concrete and abstract")
                    line("and also interfaces are supported")
                }
                paragraph {
                    +"A special type of classes are data classes."
                    +"Data classes are extremely useful. "
                }
            }

            section("Functions") {
                paragraph {
                    +"In Kotlin functions can be top level"
                    +"or defined in a class. "
                }
                paragraph {
                    +"Extension functions allow adding functions to existing classes."
                    +"In this way one can, for example, define new functions for strings."
                }
            }
            section("Lambdas and Higher-order Functions") {
                section("Lambdas") {
                    paragraph {
                        +"Lambdas created function objects. "
                        +"Function types FunctionN are the types for function objects. "
                    }
                }
                section("Higher-order functions") {
                    paragraph {
                        +"Higher-order functions are functions"
                        +"with functions as parameters. "
                        +"With extension functions higher-order functions "
                        +"can be added to collections. "
                    }
                    section("Higher-order functions for collections") {
                        paragraph {
                            +"Higher-order functions for collections are defined by extension functions."
                            +"For example, functions are map, filter, etc. "
                        }
                    }
                }
            }
            section("Conclusion") {
                paragraph {
                    +"Kotlin is a fine language."
                }
            }
        }
    val text = doc.render()
    println(text)

     */
}