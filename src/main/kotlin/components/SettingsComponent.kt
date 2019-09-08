package components

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import model.Settings

@State(name = "AndroidCodesGeneratorSettings",
    storages = [Storage(value = "androidCodesGeneratorSettings.xml")])
class SettingsComponent : ProjectComponent, PersistentStateComponent<SettingsComponent> {
    var settings = Settings()
    override fun getState() = this
    override fun loadState(state: SettingsComponent) {
        XmlSerializerUtil.copyBean(state, this)
    }

    override fun initComponent() {

    }
}