package com.hai.ithust.recipes.merchant.fragment

/**
 * @author conghai on 1/28/21.
 */
fun callback(
    appPackageName: String,
    packageName: String,
    directoryName: String,
    screenName: String
): String {
    return """
        package ${packageName}.${directoryName}
        
        import $appPackageName.base.IBaseCallback

        interface ${screenName}Callback : IBaseCallback {
            
        }
    """.trimIndent()
}

fun presenter(
    appPackageName: String,
    packageName: String,
    directoryName: String,
    screenName: String
): String {
    return """
        package ${packageName}.${directoryName}

        import $appPackageName.base.BasePresenter

        class ${screenName}Presenter(callback: ${screenName}Callback) : BasePresenter<${screenName}Callback>(callback) {
            
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

        import android.os.Bundle
        import android.view.LayoutInflater
        import android.view.View
        import android.view.ViewGroup
        import $appPackageName.FoodyApp
        import $appPackageName.base.BaseBindingFragment
        import $appPackageName.databinding.Fragment${screenName}Binding
        import $appPackageName.utils.extension.back

        class ${screenName}Fragment : BaseBindingFragment<Fragment${screenName}Binding, ${screenName}Presenter>(), ${screenName}Callback {
            override fun initView(inflater: LayoutInflater, container: ViewGroup?): Fragment${screenName}Binding {
                return Fragment${screenName}Binding.inflate(inflater, container, false)
            }

            override fun createPresenter(): ${screenName}Presenter = ${screenName}Presenter(this).also {
                FoodyApp.get().userComponent.inject(it)
            }

            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)
                initUi()
                registerEvents()
            }

            private fun initUi() {

            }

            private fun registerEvents() {
                binding.actionBar.setActionListener { back() }
            }
        }
    """.trimIndent()
}