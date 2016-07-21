package io.gitlab.artismarti.smellyj

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

/**
 * @author artur
 */
class HelloAction : AnAction("Hello") {
	override fun actionPerformed(event: AnActionEvent?) {

		Messages.showInfoMessage(SmellRegistry.smellSet?.smellSets?.entries?.toString() ?: "", "Title")
	}
}