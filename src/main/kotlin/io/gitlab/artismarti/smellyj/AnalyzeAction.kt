package io.gitlab.artismarti.smellyj

import com.gitlab.artismarti.smartsmells.api.DetectorFacade
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import java.nio.file.Paths

/**
 * @author artur
 */
class AnalyzeAction : AnAction("Inspect Code with SmartSmells") {

	override fun actionPerformed(event: AnActionEvent) {
		event.project?.basePath?.run { execute(this, event.project) }
	}

	private fun execute(path: String, project: Project?) {
		object : Task.Backgroundable(project, "SmartSmellsAnalysis, true") {
			override fun run(indicator: ProgressIndicator) {
				indicator.text = "Starting SmartSmellsAnalysis..."
				indicator.fraction = 0.0
				val facade = DetectorFacade.fullStackFacade()
				indicator.fraction = 0.10
				val smellResult = facade.run(Paths.get(path))
				indicator.fraction = 0.80
				SmellRegistry.register(smellResult)
				indicator.fraction = 1.0
			}
		}.setCancelText("Stopping analysis...").queue()
	}

}