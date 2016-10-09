package io.gitlab.arturbosch.smellyj.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import io.gitlab.arturbosch.smartsmells.api.DetectorFacade
import io.gitlab.arturbosch.smartsmells.config.Smell
import java.nio.file.Paths

/**
 * @author Artur Bosch
 */
class MetricAction : AnAction("Metric") {

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

				DetectorFacade.metricFacade().run(Paths.get(this)).prettyPrint(Smell.CLASS_INFO)

			}
		}

	}

}