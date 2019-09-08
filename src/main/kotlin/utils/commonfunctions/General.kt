package utils.commonfunctions

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElementFinder
import com.intellij.psi.search.GlobalSearchScope
import model.ClassInfo
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

fun JTextField.addTextChangedListener(onChange: (String) -> Unit) {
    val onChangeAction = { onChange(text) }
    document?.addDocumentListener(object : DocumentListener {
        override fun changedUpdate(e: DocumentEvent?) { onChangeAction() }
        override fun insertUpdate(e: DocumentEvent?) { onChangeAction() }
        override fun removeUpdate(e: DocumentEvent?) { onChangeAction() }
    })
}

fun Project.getClassElement(classPackage: String, className: String): PsiClass? {
    val psiElementFinders = getExtensions(ExtensionPointName.create<PsiElementFinder>("com.intellij.java.elementFinder"))
    return psiElementFinders.mapNotNull {
        val pack = it.findPackage(classPackage)
        if (pack == null) {
            null
        } else {
            val classes = it.getClasses(className, pack, GlobalSearchScope.allScope(this))
            classes.firstOrNull()
        }
    }.firstOrNull()
}

fun ClassInfo.isAssignableFromClass(psiClass: PsiClass, project: Project): Boolean {
    val thisPsiClass = project.getClassElement(this.packageName, this.name)
    return psiClass == thisPsiClass ||
            thisPsiClass != null && psiClass.isInheritor(thisPsiClass, true)
}