package io.gitlab.artismarti.smellyj

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

/**
 * @author artur
 */
class MyWindow : ToolWindowFactory {

	private val canvas = MyCanvas()

	override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
		val contentFactory = ContentFactory.SERVICE.getInstance()
		val content = contentFactory.createContent(canvas, "io.gitlab.artismarti.smellyj.MyWindow", false)
		toolWindow.contentManager.addContent(content)
	}
}
