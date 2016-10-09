package io.gitlab.arturbosch.smellyj.renderers

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.markup.GutterIconRenderer
import io.gitlab.arturbosch.smartsmells.common.DetectionResult
import java.util.Objects
import javax.swing.Icon

/**
 * @author Artur Bosch
 */
class MyGutterIconRenderer(val detectionResult: DetectionResult, val smellIcon: Icon, val myAction: AnAction) : GutterIconRenderer() {

	override fun hashCode(): Int {
		return Objects.hash(detectionResult, smellIcon, myAction)
	}

	override fun equals(other: Any?): Boolean {
		return Objects.equals(this, other)
	}

	override fun getIcon(): Icon {
		return smellIcon
	}

	override fun getTooltipText(): String {
		return detectionResult.asCompactString()
	}

	override fun getClickAction(): AnAction {
		return myAction
	}
}