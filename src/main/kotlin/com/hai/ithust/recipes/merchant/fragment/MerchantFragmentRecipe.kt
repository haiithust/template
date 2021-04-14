package com.hai.ithust.recipes.merchant.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.camelCaseToUnderlines
import java.io.File

/**
 * @author conghai on 1/28/21.
 */
fun RecipeExecutor.merchantFragment(
    moduleData: ModuleTemplateData,
    screenName: String,
    directoryName: String
) {
    val (projectData, srcOut, resOut, manifestOut) = moduleData
    val packageName = srcOut.path.substringAfter("java/").replace("/", ".")
    val appPackageName = projectData.applicationPackage.orEmpty()

    val layoutRes =
        "fragment_${camelCaseToUnderlines(screenName)}"
    save(
        source = merchantFragmentLayout(),
        to = resOut.resolve("layout/$layoutRes.xml")
    )

    save(
        source = callback(appPackageName, packageName, directoryName, screenName),
        to = srcOut.resolve("${directoryName}/${screenName}Callback.kt")
    )

    save(
        source = presenter(appPackageName, packageName, directoryName, screenName),
        to = srcOut.resolve("${directoryName}/${screenName}Presenter.kt")
    )

    save(
        source = fragment(appPackageName, packageName, directoryName, screenName),
        to = srcOut.resolve("${directoryName}/${screenName}Fragment.kt")
    )

    open(File("${directoryName}/${screenName}Fragment.kt"))
}