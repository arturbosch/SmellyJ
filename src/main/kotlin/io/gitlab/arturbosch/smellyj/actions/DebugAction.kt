package io.gitlab.arturbosch.smellyj.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import io.gitlab.arturbosch.smellyj.SmellRegistry

/**
 * @author Artur Bosch
 */
class DebugAction : AnAction("Debug") {
	override fun actionPerformed(event: AnActionEvent?) {
		Messages.showInfoMessage(SmellRegistry.smellSet?.smellSets?.entries?.toString() ?:
				"Analyze project first!", "Title")
	}
}