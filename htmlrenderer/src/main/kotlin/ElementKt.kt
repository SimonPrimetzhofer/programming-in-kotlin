import java.io.File

private fun String.text(): Text = Text(this)
private fun String.p(): Paragraph = Paragraph(this)
private fun String.h(level: Int) = Heading(this, level)
private fun String.h1() = Heading(this)
private fun String.h2() = Heading(this, 2)
private fun String.h3() = Heading(this, 3)
private fun String.h4() = Heading(this, 4)
private fun String.h5() = Heading(this, 5)
private fun String.h6() = Heading(this, 6)

fun String.indentEachLine(depth: Int = 2, symbol: String = " ") = this.split("\n").joinToString("\n") { symbol.repeat(depth) + it }

fun main() {
    val page = Page(
        "My Page", "Welcome to the Kotlin course".h1(), Div(
            "Kotlin is".p(), HTMLList(
                true,
                ListItem(
                    "General-purpose programming language".h3(),
                    HTMLList(false, ListItem("Backend, Mobile, Stand-Alone, Web, ...".text()))
                ),
                ListItem(
                    "Modern, multi-paradigm".h3(),
                    HTMLList(
                        false,
                        ListItem("Object-oriented, functional programming (functions as first-class citizens, …), etc.".text()),
                        ListItem("Statically typed but automatically inferred types".text())
                    )
                ),
                ListItem(
                    "Emphasis on conciseness / expressiveness / practicality".h3(),
                    HTMLList(
                        false,
                        ListItem("Goodbye Java boilerplate code (getter methods, setter methods, final, etc.)".text()),
                        ListItem("Common tasks should be short and easy".text()),
                        ListItem("Mistakes should be caught as early as possible".text()),
                        ListItem("But no cryptic operators as in Scala".text())
                    )
                ),
                ListItem(
                    "100% interoperable with Java".h3(),
                    HTMLList(
                        false,
                        ListItem("You have a Java project? Make it a Java/Kotlin project in minutes with 100% interop".text()),
                        ListItem("Kotlin-to-Java as well as Java-to-Kotlin calls".text()),
                        ListItem("For example, Kotlin reuses Java’s existing standard library (ArrayList, etc.) and extends it with extension functions (opposed to, e.g., Scala that uses its own list implementations)".text())
                    )
                ),
            )
        )
    )

    File("./output.html").writeText(HTMLRenderer.render(page))
}