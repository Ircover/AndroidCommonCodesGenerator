package views

import com.intellij.openapi.project.Project
import components.SettingsComponent
import model.ClassInfo
import model.Settings
import javax.swing.BoxLayout
import javax.swing.JPanel

class ActivitySettingsPanel(private val project: Project) : JPanel() {
    private var activityBaseClass: String
    private var activityBaseClassPackage: String
    private val activityClassRow: ClassRow
    private val settings: Settings get() {
        val settingsComponent = project.getComponent(SettingsComponent::class.java)
        return settingsComponent.settings
    }
    init {
        val settings = settings
        activityBaseClass = settings.activityBaseClass
        activityBaseClassPackage = settings.activityBaseClassPackage
        activityClassRow = ClassRow(project, ::activityBaseClass, ::activityBaseClassPackage,
            ClassInfo("Activity", "android.app"))
        initComponents()
    }

    fun applySettings() {
        val settings = settings
        settings.activityBaseClass = activityBaseClass
        settings.activityBaseClassPackage = activityBaseClassPackage
    }

    private fun initComponents() {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        add(activityClassRow)
    }
}