package io.gitlab.arturbosch.smellyj.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
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
		object : Task.Backgroundable(project, "SmartSmellsAnalysis, true") {
			override fun run(indicator: ProgressIndicator) {
				indicator.text = "Starting SmartSmellsAnalysis..."
				indicator.fraction = 0.0

				val configPath = Paths.get(System.getProperty("user.dir"))
						.resolve("/quide-config/Detectors-1.0-bin/rulesets/SmartSmells.yml")
				println(configPath)

				val facade: DetectorFacade
				if (Files.exists(configPath)) {
					println("Using config for detector facade!")
					facade = DetectorFacade.fromConfig(DetectorConfig.load(configPath))
				} else {
					println("No config found, using defaults...")
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