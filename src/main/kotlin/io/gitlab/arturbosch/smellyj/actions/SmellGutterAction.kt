package io.gitlab.arturbosch.smellyj.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * @author Artur Bosch
 */
class SmellGutterAction : AnAction("Smell") {
	override fun actionPerformed(e: AnActionEvent?) {
		println("Clicked!")
	}
}