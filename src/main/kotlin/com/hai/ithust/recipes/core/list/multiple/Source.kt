package com.hai.ithust.recipes.core.list.multiple

/**
 * @author conghai on 1/28/21.
 */
fun multipleItem(
    packagePath: String
): String {
    return """
        package $packagePath

        abstract class MultipleItem(val viewType: Int)
    """.trimIndent()
}

fun multipleHolder(
    packagePath: String
): String {
    return """
        package $packagePath

        import androidx.recyclerview.widget.RecyclerView
        import androidx.viewbinding.ViewBinding

        abstract class MultipleHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
            abstract operator fun invoke(item: MultipleItem)
        }
    """.trimIndent()
}