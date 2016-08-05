package io.gitlab.arturbosch.smellyj.renderers

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.markup.GutterIconRenderer
import io.gitlab.arturbosch.smartsmells.common.Smelly
import java.util.Objects
import javax.swing.Icon

/**
 * @author artur
 */
class MyGutterIconRenderer(val smelly: Smelly, val smellIcon: Icon, val myAction: AnAction) : GutterIconRenderer() {

	override fun hashCode(): Int {
		return Objects.hash(smelly, smellIcon, myAction)
	}

	override fun equals(other: Any?): Boolean {
		return Objects.equals(this, other)
	}

	override fun getIcon(): Icon {
		return smellIcon
	}

	override fun getTooltipText(): String {
		return smelly.asCompactString()
	}

	override fun getClickAction(): AnAction {
		return myAction
	}
}