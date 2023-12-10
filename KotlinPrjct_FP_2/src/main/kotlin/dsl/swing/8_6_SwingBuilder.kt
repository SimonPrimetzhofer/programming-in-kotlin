package dsl.swing

import java.awt.BorderLayout
import java.awt.Container
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionListener
import javax.swing.*

fun frame(title : String, x : Int = 0, y : Int = 0, init : JFrameExt.() -> Unit) : JFrameExt {
    val frame  = JFrameExt(title)
    frame.setLocation(x, y)
    frame.init()
    frame.pack()
    return frame
}

open class JFrameExt(title: String) : JFrame(title) {

    abstract class ComponentBuilder(val jComp: JPanel) {
        fun border() {
            jComp.border = BorderFactory.createEtchedBorder()
        }
    }

    fun exitOnClose() {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }

    fun open() {
        isVisible = true
    }

    fun field(width: Int) = JTextField(width)
    fun button(label: String) = JButton(label)
    fun label(text: String) = JLabel(text)
    fun panel() = JPanel()

    fun menuBar(init: JMenuBar.() -> Unit): JMenuBar {
        jMenuBar = JMenuBar()
        jMenuBar.init()
        return jMenuBar
    }

    fun JMenuBar.menu(name: String, init: JMenu.() -> Unit): JMenu {
        val menu = JMenu(name)
        add(menu)
        menu.init()
        return menu
    }

    fun JMenu.menu(name: String, init: JMenu.() -> Unit): JMenu {
        val menu = JMenu(name)
        add(menu)
        menu.init()
        return menu
    }

    fun JMenu.item(name: String): JMenuItem {
        val item = JMenuItem(name)
        add(item)
        return item
    }

    operator fun <A : AbstractButton> A.plus(action: ActionListener): A {
        addActionListener(action)
        return this
    }

    infix fun <A : AbstractButton> A.onEvent(action: A.() -> Unit): A {
        addActionListener { ae -> this.action() }
        return this
    }

    operator fun JTextField.plus(action: ActionListener): JTextField {
        addActionListener(action)
        return this
    }

    infix fun JTextField.onEvent(action: JTextField.() -> Unit): JTextField {
        addActionListener { ae -> this.action() }
        return this
    }

    operator fun AbstractButton.minus(idx: Int): AbstractButton {
        actionListeners.get(idx)?.run { removeActionListener(this) }
        return this
    }

    fun JFrame.content(init: Container.() -> Unit): Container {
        val pane = contentPane
        contentPane.init()
        return contentPane
    }

    fun borderLayout(init: BorderLayoutBuilder.() -> Unit): JPanel {
        val panel = JPanel()
        panel.layout = BorderLayout()
        val builder = BorderLayoutBuilder(panel)
        builder.init()
        return panel
    }

    class BorderLayoutBuilder(val panel: JPanel) : ComponentBuilder(panel) {

        fun <C : JComponent> Container.north(jComp: C, init: C.() -> Unit = {}): C {
            add(jComp, BorderLayout.NORTH)
            jComp.init()
            return jComp
        }

        fun <C : JComponent> Container.south(jComp: C, init: C.() -> Unit = {}): C {
            add(jComp, BorderLayout.SOUTH)
            jComp.init()
            return jComp
        }

        fun <C : JComponent> Container.east(jComp: C, init: C.() -> Unit = {}): C {
            add(jComp, BorderLayout.EAST)
            jComp.init()
            return jComp
        }

        fun <C : JComponent> Container.west(jComp: C, init: C.() -> Unit = {}): C {
            add(jComp, BorderLayout.WEST)
            jComp.init()
            return jComp
        }

        fun <C : JComponent> Container.center(jComp: C, init: C.() -> Unit = {}): C {
            add(jComp, BorderLayout.CENTER)
            jComp.init()
            return jComp
        }
    }

    fun gridLayout(rows: Int = 2, cols: Int = 2, init: GridLayoutBuilder.() -> Unit): JPanel {
        val panel = JPanel()
        panel.layout = GridLayout(rows, cols)
        val gridBuilder = GridLayoutBuilder(panel, rows, cols)
        gridBuilder.init()
        return panel
    }

    class GridLayoutBuilder(val panel: JPanel, val rows: Int, val cols: Int) : ComponentBuilder(panel) {
        fun <C : JComponent> elem(jComp: C, row: Int, col: Int, init: C.() -> Unit = {}): C {
            panel.add(jComp, row, col)
            jComp.init()
            return jComp
        }
    }

    fun flowLayout(align: Int = 0, init: FlowLayoutBuilder.() -> Unit): JPanel {
        val panel = JPanel()
        panel.layout = FlowLayout(align)
        val gridBuilder = FlowLayoutBuilder(panel)
        gridBuilder.init()
        return panel
    }

    class FlowLayoutBuilder(val panel: JPanel) : ComponentBuilder(panel) {

        fun <C : JComponent> comp(jComp: C, init: C.() -> Unit = {}): C {
            panel.add(jComp)
            jComp.init()
            return jComp
        }

    }

    fun <C : JComponent> Container.component(jComp: C, init: C.() -> Unit = {}): C {
        add(jComp)
        jComp.init()
        return jComp
    }
}

fun main() {

    val frame =
        frame("Convert temperatures", 200, 200) {

            exitOnClose()

            val celsius = field(6)
            val fahrenh = field(6)
            val message = label(" ")

            menuBar {
                menu("File") {
                    item("Exit") onEvent { System.exit(0) }
                }
                menu("Edit") {
                    item("Reset") onEvent {
                        celsius.text = "0"
                        fahrenh.text = "32"
                        message.text = "${celsius.text} C = ${fahrenh.text} F"
                    }
                }
            }

            content {
                borderLayout {
                    north(flowLayout(FlowLayout.CENTER) {
                            border()
                            comp(button("Reset")) onEvent {
                                celsius.text = "0"
                                fahrenh.text = "32"
                                message.text = "${celsius.text} C = ${fahrenh.text} F"
                            }
                    })
                    center(flowLayout {
                        border()
                        comp(celsius) { text = "0" } onEvent {
                            val c = text.filter { it.isDigit() || it == '-' }.toInt()
                            val f = c * 9 / 5 + 32
                            fahrenh.text = f.toString()
                            message.text = "${celsius.text} C = ${fahrenh.text} F"
                        }
                        comp(JLabel("Celsius  = "))
                        comp(fahrenh) { text = "32" } onEvent {
                            val f = fahrenh.text.filter { it.isDigit() || it == '-' }.toInt()
                            val c = (f - 32) * 5 / 9
                            celsius.text = c.toString()
                            message.text = "${celsius.text} C = ${fahrenh.text} F"
                        }
                        comp(JLabel("Fahrenheit"))

                    })
                    south(message) {
                        border()
                        text = "${celsius.text} C = ${fahrenh.text} F"
                    }
                }
            }

        }

    frame.open()

}
