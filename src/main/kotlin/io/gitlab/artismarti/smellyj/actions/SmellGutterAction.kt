package io.gitlab.artismarti.smellyj.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * @author artur
 */
class SmellGutterAction : AnAction("Smell") {
	override fun actionPerformed(e: AnActionEvent?) {
		println("Clicked!")
	}
}