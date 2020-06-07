package me.artspb.idea.build.number.plugin

import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.StringSelection
import javax.swing.Icon

object AboutInfo {

    fun copyToClipboard() {
        CopyPasteManager.getInstance().setContents(StringSelection(toString()))
        BuildNumberNotification.info("About Info Copied", "About info has been copied to clipboard.")
    }

    override fun toString(): String {
        val infoSurfaceClass = Class.forName("com.intellij.ide.actions.AboutPopup\$InfoSurface")
        val infoSurfaceConstructor = infoSurfaceClass.getDeclaredConstructor(
                Icon::class.java, Boolean::class.javaPrimitiveType
        )
        infoSurfaceConstructor.isAccessible = true

        val getTextMethod = infoSurfaceClass.getDeclaredMethod("getText")
        getTextMethod.isAccessible = true

        val infoSurface = infoSurfaceConstructor.newInstance(null, false)
        return getTextMethod.invoke(infoSurface) as String
    }
}
