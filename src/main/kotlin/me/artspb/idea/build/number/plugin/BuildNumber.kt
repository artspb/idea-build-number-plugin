package me.artspb.idea.build.number.plugin

import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.StringSelection

object BuildNumber {

    private val notificationGroup = NotificationGroup.balloonGroup("BuildNumberPluginNotification")

    fun copyToClipboard() {
        CopyPasteManager.getInstance().setContents(StringSelection(toString()))
        createAndShowNotification()
    }

    private fun createAndShowNotification() {
        Notifications.Bus.notify(notificationGroup.createNotification("Build Number Copied",
                "Build number was copied to clipboard",
                NotificationType.INFORMATION))
    }

    override fun toString(): String = ApplicationInfo.getInstance().build.toString()
}
