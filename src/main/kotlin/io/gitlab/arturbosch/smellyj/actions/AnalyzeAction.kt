package io.gitlab.arturbosch.smellyj.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import io.gitlab.arturbosch.smartsmells.api.DetectorFacade
import io.gitlab.arturbosch.smartsmells.config.DetectorConfig
import io.gitlab.arturbosch.smellyj.SmellRegistry
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author artur
 */
class AnalyzeAction : AnAction("Inspect Code with SmartSmells") {

	override fun actionPerformed(event: AnActionEvent) {
		event.project?.basePath?.run { execute(this, event.project) }
	}

	private fun execute(path: String, project: Project?) {

		val input = Messages.showInputDialog(project, "Enter path to SmartSmells.yml",
				"Config", Messages.getQuestionIcon())

		if (input.isNullOrEmpty()) return

		object : Task.Backgroundable(project, "SmartSmellsAnalysis, true") {
			override fun run(indicator: ProgressIndicator) {
				indicator.text = "Starting SmartSmellsAnalysis..."
				indicator.fraction = 0.0

				println(input)
				val configPath = Paths.get(input)

				val facade: DetectorFacade
				if (Files.exists(configPath)) {
					println("Using configPath for detector facade!")
					facade = DetectorFacade.fromConfig(DetectorConfig.load(configPath))
				} else {
					println("No configPath found, using defaults...")
					facade = DetectorFacade.fullStackFacade()
				}
				indicator.fraction = 0.40
				val smellResult = facade.run(Paths.get(path))
				indicator.fraction = 0.90
				SmellRegistry.register(smellResult)
				indicator.fraction = 1.0
			}
		}.setCancelText("Stopping analysis...").queue()
	}

}