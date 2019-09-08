package utils.commonfunctions

import javax.swing.JTextField
import kotlin.reflect.KMutableProperty0

fun createTextField(stringProperty: KMutableProperty0<String>, onTextChanged: () -> Unit) =
    JTextField(stringProperty.get()).apply {
        addTextChangedListener {
            stringProperty.set(it)
            onTextChanged()
        }
    }