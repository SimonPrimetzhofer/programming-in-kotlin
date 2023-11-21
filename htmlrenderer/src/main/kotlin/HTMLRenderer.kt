object HTMLRenderer {
    fun render(element: Element): String = when (element) {
        is Text -> element.text
        is ContainerElement -> element.elements.joinToString("", element.openTag, element.closeTag){ render(it) }
        is TaggedTextElement -> "${element.openTag}${element.text}${element.closeTag}"
    }

    fun render(page: Page): String {
        return "<html>" +
                    "<head>" +
                        "<title>${page.title}</title>" +
                    "</head>" +
                    "<body>" +
                        page.elements.joinToString("") { render(it) } +
                    "</body>" +
                "</html>".indentEachLine()
    }
}