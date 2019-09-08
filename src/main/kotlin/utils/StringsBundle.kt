package utils

import com.intellij.CommonBundle
import org.jetbrains.annotations.PropertyKey
import java.lang.ref.WeakReference
import java.util.*

object StringsBundle {
    private const val BUNDLE_NAME = "plugin_strings"
    private var bundle: WeakReference<ResourceBundle>? = null

    fun getString(@PropertyKey(resourceBundle = BUNDLE_NAME) key: String,
                  vararg params: Any): String {
        return CommonBundle.message(getBundle(), key, *params)
    }

    private fun getBundle(): ResourceBundle {
        return bundle?.get() ?: ResourceBundle.getBundle(BUNDLE_NAME).apply {
            bundle = WeakReference(this)
        }
    }
}