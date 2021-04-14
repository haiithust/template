package com.hai.ithust.recipes.core.list.multiple

/**
 * @author conghai on 1/28/21.
 */
fun adapter(
    appPackageName: String,
    packageName: String,
    directoryName: String,
    holderNames: List<String>,
    adapterName: String
): String {
    val inflateHolders = """
        ${holderNames.joinToString(separator = "\n") { inflateHolder(it) }}
        else -> throw IllegalArgumentException("Invalid view type ")
    """.trimIndent()

    return """
        package ${packageName}.${directoryName}

        import android.view.LayoutInflater
        import android.view.ViewGroup
        import androidx.recyclerview.widget.RecyclerView
        import $appPackageName.databinding.ItemFeatureOneBinding
        import $appPackageName.databinding.ItemFeatureTwoBinding
        import $appPackageName.view.recyclerview.MultipleHolder
        import $appPackageName.view.recyclerview.MultipleItem

        class ${adapterName}Adapter : RecyclerView.Adapter<MultipleHolder>() {
            private val items = mutableListOf<MultipleItem>()

            fun setData(data: List<MultipleItem>) {
                items.apply {
                    clear()
                    addAll(data)
                }
                notifyDataSetChanged()
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleHolder {
                return when (viewType) {
                    $inflateHolders
                }
            }

            override fun onBindViewHolder(holder: MultipleHolder, position: Int) = holder(items[position])

            override fun getItemCount(): Int = items.size
        }
    """.trimIndent()
}

private fun inflateHolder(holderName: String) =
    """
    ${holderNameToViewType(holderName)} -> ${holderNameToHolder(holderName)}(
        ${holderNameToViewBinding(holderName)}.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
""".trimIndent()