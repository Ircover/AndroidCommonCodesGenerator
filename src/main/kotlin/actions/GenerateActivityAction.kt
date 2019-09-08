package actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import dialogs.ActivitySettingsDialog

class GenerateActivityAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        e.project?.let { project ->
            ActivitySettingsDialog(project).show()
        }
    }

}