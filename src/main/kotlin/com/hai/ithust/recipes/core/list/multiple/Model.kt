package com.hai.ithust.recipes.core.list.multiple

/**
 * @author conghai on 1/28/21.
 */
fun multipleModelDeclare(
    appPackageName: String,
    packageName: String,
    directoryName: String,
    holderNames: List<String>
): String {
    val holderViewTypes = holderNames.joinToString(separator = "\n") {
        """
        const val ${holderNameToViewType(it)} = ${holderNameToLayout(it)}
        """.trimIndent()
    }

    val viewModels = holderNames.joinToString(separator = "\n") {
        """
        class ${holderNameToModel(it)} : MultipleItem(${holderNameToViewType(it)})
        """.trimIndent()
    }

    return """
        package ${packageName}.${directoryName}
        
        import $appPackageName.R
        import $appPackageName.view.recyclerview.MultipleItem
        
        $holderViewTypes
        
        $viewModels
    """.trimIndent()
}