package com.hai.ithust.recipes.core.list.multiple

/**
 * @author conghai on 1/28/21.
 */
fun holder(
    appPackageName: String,
    packageName: String,
    directoryName: String,
    holderName: String
): String {
    return """
        package ${packageName}.${directoryName}

        import $appPackageName.databinding.${holderNameToViewBinding(holderName)}
        import $appPackageName.view.recyclerview.MultipleHolder
        import $appPackageName.view.recyclerview.MultipleItem
        
        class ${holderNameToHolder(holderName)}(private val binding: ${holderNameToViewBinding(holderName)}) : MultipleHolder(binding) {
            override fun invoke(item: MultipleItem) {
                val model = item as ${holderNameToModel(holderName)}
            }
        }
    """.trimIndent()
}