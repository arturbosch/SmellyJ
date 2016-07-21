package io.gitlab.artismarti.smellyj.renderers

import com.gitlab.artismarti.smartsmells.common.Smelly
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.markup.GutterIconRenderer
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
		return smelly.toString()
	}

	override fun getClickAction(): AnAction {
		return myAction
	}
}