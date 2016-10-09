package io.gitlab.arturbosch.smellyj.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.markup.EffectType
import com.intellij.openapi.editor.markup.HighlighterLayer
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.util.ui.ColorIcon
import io.gitlab.arturbosch.smellyj.SmellRegistry
import io.gitlab.arturbosch.smellyj.renderers.MyGutterIconRenderer
import org.apache.log4j.Logger
import java.awt.Color
import java.awt.Font

/**
 * @author Artur Bosch
 */
class InspectAction : AnAction("Inspect current file") {

	private val logger = Logger.getLogger(AnalyzeAction::class.java.simpleName)

	override fun actionPerformed(event: AnActionEvent?) {
		event?.project?.execute()
	}

	private fun Project.execute() {
		val selectedTextEditor = FileEditorManager.getInstance(this).selectedTextEditor
		val document = selectedTextEditor?.document
		val markupModel = selectedTextEditor?.markupModel

		if (document != null && markupModel != null) {
			val currentFile = FileDocumentManager.getInstance().getFile(document)
			val fileName = currentFile?.path

			fileName?.run {
				markupModel.removeAllHighlighters()
				val smellsForFile = SmellRegistry.forPath(fileName)

				smellsForFile.forEach {
					logger.info(it)
					val pos = it.positions
					val highlighter = markupModel.addLineHighlighter(pos.startLine - 1,
							HighlighterLayer.WARNING,
							TextAttributes(Color.yellow, Color.GRAY, Color.GREEN, EffectType.ROUNDED_BOX, Font.PLAIN))
					highlighter.gutterIconRenderer = MyGutterIconRenderer(it, ColorIcon(10, Color.ORANGE), SmellGutterAction())
				}

			}
		}

	}
}