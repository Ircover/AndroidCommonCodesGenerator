package dialogs

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import views.ActivitySettingsPanel
import java.awt.Dimension

class ActivitySettingsDialog(project: Project) : DialogWrapper(true) {
    private val mainPanel = ActivitySettingsPanel(project)
    init {
        init()
    }
    override fun createCenterPanel(): ActivitySettingsPanel {
        return mainPanel
    }

    override fun doOKAction() {
        super.doOKAction()
        mainPanel.applySettings()
    }

    override fun getPreferredSize() = Dimension(600, 100)
}