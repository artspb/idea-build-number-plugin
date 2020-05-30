package me.artspb.idea.build.number.plugin

import com.intellij.notification.*
import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.CustomStatusBarWidget
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.intellij.openapi.wm.impl.status.TextPanel
import com.intellij.ui.ClickListener
import org.jetbrains.annotations.Nls
import java.awt.datatransfer.StringSelection
import java.awt.event.MouseEvent
import javax.swing.JComponent

class BuildNumberStatusWidgetFactory : StatusBarWidgetFactory {

    companion object {
        private const val ID = "BuildNumber"
    }

    override fun getId(): String = ID

    @Nls
    override fun getDisplayName(): String = "Build Number"

    override fun isAvailable(project: Project): Boolean = true

    override fun createWidget(project: Project): StatusBarWidget = BuildNumberStatusWidget()

    override fun canBeEnabledOn(statusBar: StatusBar): Boolean = true

    override fun disposeWidget(widget: StatusBarWidget) {}

    private class BuildNumberStatusWidget : TextPanel(), CustomStatusBarWidget {

        companion object {
            private val buildNumber = ApplicationInfo.getInstance().build.toString()
        }

        init {
            object : ClickListener() {
                override fun onClick(event: MouseEvent, clickCount: Int): Boolean {
                    CopyPasteManager.getInstance().setContents(StringSelection(buildNumber))
                    createAndShowNotification()
                    return true
                }
            }.installOn(this, true)
        }

        override fun ID(): String = ID

        override fun install(statusBar: StatusBar) {
            text = buildNumber
        }

        override fun getComponent(): JComponent = this

        override fun dispose() {}

        private fun createAndShowNotification() {
            val notificationGroup = NotificationGroup("BuildNumberPluginNotification",
                    NotificationDisplayType.BALLOON)
            Notifications.Bus.notify(Notification(notificationGroup.displayId,
                    "Build Number Copied",
                    "Build number was copied to clipboard",
                    NotificationType.INFORMATION))
        }
    }
}
