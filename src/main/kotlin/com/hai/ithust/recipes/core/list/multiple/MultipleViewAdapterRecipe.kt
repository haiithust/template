package com.hai.ithust.recipes.core.list.multiple

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.camelCaseToUnderlines
import java.io.File

/**
 * @author conghai on 1/28/21.
 */

private const val BASE_ITEM_DIRECTORY = "view/recyclerview"
private const val ADAPTER_DIRECTORY = "adapter"

fun RecipeExecutor.multipleViewAdapter(
    moduleData: ModuleTemplateData,
    adapterName: String,
    holderNameList: String
) {
    val (projectData, srcOut, resOut) = moduleData
    val packageName = srcOut.path.substringAfter("java/").replace("/", ".")
    val holderNames = holderNameList.split(",").map { it.trim() }

    if (holderNames.size > 1) {
        val packageNameDir = projectData.applicationPackage.orEmpty().replace(".", "/")
        val packageSourcePath =
            srcOut.path.substring(0, srcOut.path.indexOf(packageNameDir) + packageNameDir.length)
        val dir = File("$packageSourcePath/$BASE_ITEM_DIRECTORY")
        dir.mkdirs()
        /** Start Base Item **/
        val baseItemPath = "$packageSourcePath/$BASE_ITEM_DIRECTORY/MultipleItem.kt"
        if (File(baseItemPath).exists().not()) {
            save(
                source = multipleItem(
                    "${projectData.applicationPackage}.${
                        BASE_ITEM_DIRECTORY.replace(
                            "/",
                            "."
                        )
                    }"
                ),
                to = File(baseItemPath)
            )
        }
        val baseHolderPath = "$packageSourcePath/$BASE_ITEM_DIRECTORY/MultipleHolder.kt"
        if (File(baseHolderPath).exists().not()) {
            save(
                source = multipleHolder(
                    "${projectData.applicationPackage}.${
                        BASE_ITEM_DIRECTORY.replace(
                            "/",
                            "."
                        )
                    }"
                ),
                to = File(baseHolderPath)
            )
        }
        /** End Base Item **/

        /** Start Item Models **/
        save(
            source = multipleModelDeclare(
                projectData.applicationPackage.orEmpty(),
                packageName,
                ADAPTER_DIRECTORY,
                holderNames
            ),
            to = srcOut.resolve("$ADAPTER_DIRECTORY/${adapterName}Model.kt")
        )
        /** End Item Models **/

        /** Start Layouts **/
        holderNames.forEach {
            save(
                source = itemLayout(),
                to = resOut.resolve("layout/item_${camelCaseToUnderlines(it)}.xml")
            )
        }
        /** End Layouts **/

        /** Start Holders **/
        holderNames.forEach {
            save(
                source = holder(
                    projectData.applicationPackage.orEmpty(),
                    packageName,
                    ADAPTER_DIRECTORY,
                    it
                ),
                to = srcOut.resolve("$ADAPTER_DIRECTORY/${it}Holder.kt")
            )
        }
        /** End Holders **/

        save(
            source = adapter(
                projectData.applicationPackage.orEmpty(),
                packageName,
                ADAPTER_DIRECTORY,
                holderNames,
                adapterName
            ),
            to = srcOut.resolve("$ADAPTER_DIRECTORY/${adapterName}Adapter.kt")
        )
    } else {

    }
}

fun holderNameToViewType(name: String) = "VIEW_TYPE_${camelCaseToUnderlines(name).toUpperCase()}"

fun holderNameToModel(name: String) = "${name}Model"

fun holderNameToLayout(name: String) = "R.layout.item_${camelCaseToUnderlines(name)}"

fun holderNameToHolder(name: String) = "${name}Holder"

fun holderNameToViewBinding(name: String) = "Item${name}Binding"