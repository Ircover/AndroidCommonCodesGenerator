package views

import com.intellij.openapi.project.Project
import model.ClassInfo
import utils.*
import utils.commonfunctions.addTextChangedListener
import utils.commonfunctions.createTextField
import utils.commonfunctions.getClassElement
import utils.commonfunctions.isAssignableFromClass
import java.awt.Color
import java.awt.Component
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty0

class ClassRow(private val project: Project,
               private val classNameProperty: KMutableProperty0<String>,
               private val classPackageProperty: KMutableProperty0<String>,
               private val parentClassInfo: ClassInfo?) : JPanel() {
    private val warningLabel = JLabel().apply {
        alignmentX = Component.CENTER_ALIGNMENT
        foreground = Color.RED
    }

    init {
        initComponents()
    }

    private fun initComponents() {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        add(
            JPanel().apply {
                layout = BoxLayout(this, BoxLayout.X_AXIS)
                add(JLabel(StringsBundle.getString("Class")))
                add(
                    createTextField(classNameProperty) { refreshWarning() }
                )
                add(JLabel(StringsBundle.getString("Package")))
                add(
                    createTextField(classPackageProperty) { refreshWarning() }
                )
            }
        )
        add(warningLabel)
        refreshWarning()
    }

    private fun refreshWarning() {
        val resultClass = "${classPackageProperty.get()}.${classNameProperty.get()}"
        val psiClass = project.getClassElement(classPackageProperty.get(), classNameProperty.get())
        when {
            psiClass == null -> {
                warningLabel.text = StringsBundle.getString("WarningNotExistingClass", resultClass)
                warningLabel.isVisible = true
            }
            parentClassInfo?.isAssignableFromClass(psiClass, project) == false -> {
                warningLabel.text = StringsBundle.getString("WarningNotExtendingClass",
                    resultClass, parentClassInfo.name)
                warningLabel.isVisible = true
            }
            else -> {
                warningLabel.isVisible = false
            }
        }
    }
}