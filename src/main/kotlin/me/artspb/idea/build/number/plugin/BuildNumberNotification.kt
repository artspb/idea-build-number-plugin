package me.artspb.idea.build.number.plugin

import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications

object BuildNumberNotification {

    private val notificationGroup = NotificationGroup.balloonGroup("me.artspb.idea.build.number.plugin.BuildNumberNotification")

    fun info(title: String, content: String) {
        Notifications.Bus.notify(notificationGroup.createNotification(title, content, NotificationType.INFORMATION))
    }
}
