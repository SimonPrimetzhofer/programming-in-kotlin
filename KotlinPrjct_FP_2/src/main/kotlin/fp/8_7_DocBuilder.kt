package fp

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

abstract class ElementContainer(val nrs: List<Int>) : Renderable {
    internal var sectionNr = 1
    internal val elements = arrayListOf<Element>()

    fun section(heading: String, init: Section.() -> Unit) {
        val section = Section(nrs + listOf(sectionNr++), heading)
        elements.add(section)
        section.init()
    }

    override fun render(b: StringBuilder)  {
        for (sect in elements) {
            sect.render(b)
        }
    }

}

class Doc : ElementContainer(listOf()) {
    var title: String = ""
    var authors: String = ""
    var affiliation: String = ""

    override fun render(b: StringBuilder)  {
        b.append(nl + title + nlnl)
        b.append(authors + nl)
        b.append(affiliation + nl)
        super.render(b)
    }

}

fun document(init: Doc.() -> Unit): Doc {
    val doc = Doc()
    doc.init()
    return doc
}

class Section(nrs: List<Int>, val heading: String) : ElementContainer(nrs), Element {

    override fun render(b: StringBuilder) {
        b.append("$nl${nrs.joinToString(separator = ".")} $heading $nl")
        super<ElementContainer>.render(b)
    }

    fun paragraph(init: Paragraph.() -> Unit) {
        val paragraph = Paragraph()
        elements.add(paragraph)
        paragraph.init()

    }

}

class Paragraph : Element {
    internal val lines = arrayListOf<String>()

    fun line(line: String) {
        lines.add(line)
    }

    operator fun String.unaryPlus() {
        lines.add(this)
    }

    override fun render(b: StringBuilder) {
        for (line in lines) {
            b.append(line + nl)
        }
    }
}

fun main() {
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
}