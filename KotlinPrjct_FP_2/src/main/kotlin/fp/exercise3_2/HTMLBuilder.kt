package fp.exercise3_2

import java.io.File

fun page(init: PageBuilder.() -> Unit): Page {
    val b = PageBuilder()
    b.init()
    return Page(b.title, b.elements)
}

abstract class ContainerBuilder<E> {
    internal val elements: MutableList<E> = mutableListOf()
}

open class ElementContainerBuilder : ContainerBuilder<Element>() {
    fun heading(level: Int, text: String): Heading {
        val heading = Heading(level, text)
        elements.add(heading)
        return heading
    }

    fun text(text: String): Text {
        val textElement = Text(text)
        elements.add(textElement)
        return textElement
    }

    fun paragraph(init: ParagraphBuilder.() -> Unit): Paragraph {
        val b = ParagraphBuilder()
        b.init()
        // I decided that if multiple lines are within a paragraph, they are only separated by a whitespace (and not a newline for example)
        val paragraph = Paragraph(b.elements.joinToString(" ") { it })
        elements.add(paragraph)

        return paragraph
    }

    fun div(init: ElementContainerBuilder.() -> Unit): Div {
        val b = ElementContainerBuilder()
        b.init()
        val div = Div(b.elements)

        elements.add(div)

        return div
    }

    fun list(ordered: Boolean, init: ListBuilder.() -> Unit): HTMLList {
        val b = ListBuilder()
        b.init()
        val htmlList = HTMLList(b.elements, ordered)

        elements.add(htmlList)

        return htmlList
    }
}

class PageBuilder : ElementContainerBuilder() {
    var title: String = ""
}

class ListBuilder : ContainerBuilder<ListItem>() {
    fun item(init: ElementContainerBuilder.() -> Unit): ListItem {
        val b = ElementContainerBuilder()
        b.init()
        val listItem = ListItem(b.elements)

        elements.add(listItem)

        return listItem
    }
}

class ParagraphBuilder : ContainerBuilder<String>() {
    fun line(text: String) = elements.add(text)

    operator fun String.unaryPlus() = line(this)
}

fun main() {
    val page =
        page {
            title = "My Page"
            heading(1, "Welcome to the Kotlin course")
            div {
                paragraph {
                    +"Kotlin is"
                    +"cool and a" // added this line for testing multiple lines within a paragraph
                }
                list(true) {
                    item {
                        heading(3, "General-purpose programming language")
                        list(false) {
                            item { text("Backend") }
                            item { text("Mobile") }
                            item { text("Stand-Alone") }
                            item { text("Web") }
                            item { text("...") }
                        }
                    }
                    item {
                        heading(3, "Modern, multi-paradigm")
                        list(false) {
                            item {
                                text("Object-oriented, functional programming (functions as first-class citizens, …), etc.")
                            }
                            item {
                                text("Statically typed but automatically inferred types")
                            }
                        }
                    }
                    item {
                        heading(3, "Emphasis on conciseness / expressiveness / practicality")
                        list(false) {
                            item {
                                text("Goodbye Java boilerplate code (getter methods, setter methods, final, etc.)")
                            }
                            item {
                                text("Common tasks should be short and easy")
                            }
                            item {
                                text("Mistakes should be caught as early as possible")
                            }
                            item {
                                text("But no cryptic operators as in Scala")
                            }
                        }
                    }
                    item {
                        heading(3, "100 % Interoperable with Java")
                        list(false) {
                            item {
                                text("You have a Java project? Make it a Java/Kotlin project in minutes with 100% interop")
                            }
                            item {
                                text("Kotlin-to-Java as well as Java-to-Kotlin calls")
                            }
                            item {
                                text("For example, Kotlin reuses Java’s existing standard library (ArrayList, etc.) and extends it with extension functions (opposed to, e.g., Scala that uses its own list implementations)")
                            }
                        }

                    }
                }
            }

        }
    File("page2.html").writeText(HTMLRenderer.render(page))
}
