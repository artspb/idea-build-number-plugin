package me.artspb.idea.build.number.plugin

import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.StringSelection

object BuildNumber {

    fun copyToClipboard() {
        CopyPasteManager.getInstance().setContents(StringSelection(toString()))
        BuildNumberNotification.info("Build Number Copied", "Build number has been copied to clipboard.")
    }

    override fun toString(): String = ApplicationInfo.getInstance().build.toString()
}
