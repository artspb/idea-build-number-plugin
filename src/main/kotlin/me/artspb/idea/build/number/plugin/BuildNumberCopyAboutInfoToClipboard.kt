package me.artspb.idea.build.number.plugin

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction

class BuildNumberCopyAboutInfoToClipboard : DumbAwareAction() {
    override fun actionPerformed(e: AnActionEvent) {
        AboutInfo.copyToClipboard()
    }
}
