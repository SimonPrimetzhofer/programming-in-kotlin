object HTMLRenderer {
    fun render(element: Element) = when (element) {
        is Text -> element.text
        is Div -> "${element.openTag}\n${element.elements.map { "test" }}\n${element.closeTag}"
        is ListItem -> ""
        is HTMLList -> ""
        is Heading -> ""
        is Paragraph -> ""
    }

    fun render(page: Page): String {
        return "TODO"
    }
}