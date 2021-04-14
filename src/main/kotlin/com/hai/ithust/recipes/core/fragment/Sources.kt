package com.hai.ithust.recipes.core.fragment

/**
 * @author conghai on 1/28/21.
 */
fun viewModel(
    packageName: String,
    directoryName: String,
    screenName: String
): String {
    return """
        package ${packageName}.${directoryName}

        import androidx.lifecycle.ViewModel
        import dagger.hilt.android.lifecycle.HiltViewModel
        import javax.inject.Inject

        @HiltViewModel
        class ${screenName}ViewModel @Inject constructor(

        ) : ViewModel() {

        }
    """.trimIndent()
}

fun fragment(
    appPackageName: String,
    packageName: String,
    directoryName: String,
    screenName: String
): String {
    return """
        package ${packageName}.${directoryName}

        import android.view.LayoutInflater
        import android.view.ViewGroup
        import androidx.fragment.app.viewModels
        import dagger.hilt.android.AndroidEntryPoint
        import $appPackageName.databinding.Fragment${screenName}Binding
        import ithust.hai.core.base.BaseBindingFragment

        @AndroidEntryPoint
        class ${screenName}Fragment : BaseBindingFragment<Fragment${screenName}Binding>() {
            private val viewModel: ${screenName}ViewModel by viewModels()

            override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): Fragment${screenName}Binding {
                return Fragment${screenName}Binding.inflate(inflater, container, false)
            }

            override fun initUI() {

            }

            override fun registerEvents() {

            }

            override fun observer() {

            }
        }
    """.trimIndent()
}