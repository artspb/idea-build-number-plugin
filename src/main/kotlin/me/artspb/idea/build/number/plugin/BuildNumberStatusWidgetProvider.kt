package me.artspb.idea.build.number.plugin

import com.intellij.diagnostic.IdeMessagePanel
import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.CustomStatusBarWidget
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetProvider
import com.intellij.openapi.wm.impl.status.TextPanel
import com.intellij.ui.ClickListener
import java.awt.datatransfer.StringSelection
import java.awt.event.MouseEvent
import javax.swing.JComponent

class BuildNumberStatusWidgetProvider : StatusBarWidgetProvider {

    companion object {
        private const val ID = "BuildNumber"
    }

    override fun getWidget(project: Project): StatusBarWidget = BuildNumberStatusWidget()

    override fun getAnchor(): String = StatusBar.Anchors.after(IdeMessagePanel.FATAL_ERROR)

    private class BuildNumberStatusWidget : TextPanel(), CustomStatusBarWidget {

        companion object {
            private val buildNumber = ApplicationInfo.getInstance().build.toString()
        }

        init {
            object : ClickListener() {
                override fun onClick(event: MouseEvent, clickCount: Int): Boolean {
                    CopyPasteManager.getInstance().setContents(StringSelection(buildNumber))
                    return true
                }
            }.installOn(this)
        }

        override fun ID(): String = ID

        override fun install(statusBar: StatusBar) {
            text = buildNumber
        }

        override fun getComponent(): JComponent = this

        override fun getPresentation(type: StatusBarWidget.PlatformType): StatusBarWidget.WidgetPresentation? = null

        override fun dispose() {}
    }
}
