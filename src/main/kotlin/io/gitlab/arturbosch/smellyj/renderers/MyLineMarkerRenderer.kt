package io.gitlab.arturbosch.smellyj.renderers

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.markup.LineMarkerRenderer
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle

/**
 * @author Artur Bosch
 */
class MyLineMarkerRenderer constructor(private val myColor: Color) : LineMarkerRenderer {

	override fun paint(editor: Editor, g: Graphics, r: Rectangle) {
		val height = r.height + editor.lineHeight
		g.color = myColor
		g.fillRect(r.x, r.y, THICKNESS, height)
		g.fillRect(r.x + THICKNESS, r.y, DEEPNESS, THICKNESS)
		g.fillRect(r.x + THICKNESS, r.y + height - THICKNESS, DEEPNESS, THICKNESS)
	}

	companion object {
		private val DEEPNESS = 0
		private val THICKNESS = 1
	}
}