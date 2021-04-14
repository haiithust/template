package com.hai.ithust.recipes.core.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.camelCaseToUnderlines
import java.io.File

/**
 * @author conghai on 1/28/21.
 */
fun RecipeExecutor.hiltViewModelFragment(
    moduleData: ModuleTemplateData,
    screenName: String,
    directoryName: String
) {
    val (projectData, srcOut, resOut) = moduleData
    val packageName = moduleData.packageName
    val appPackageName = projectData.applicationPackage.orEmpty()

    val layoutRes =
        "fragment_${camelCaseToUnderlines(screenName)}"
    save(
        source = hiltFragmentLayout(),
        to = resOut.resolve("layout/$layoutRes.xml")
    )

    save(
        source = viewModel(packageName, directoryName, screenName),
        to = srcOut.resolve("${directoryName}/${screenName}ViewModel.kt")
    )

    save(
        source = fragment(appPackageName, packageName, directoryName, screenName),
        to = srcOut.resolve("${directoryName}/${screenName}Fragment.kt")
    )

    open(File("${directoryName}/${screenName}Fragment.kt"))
    open(File("${directoryName}/${screenName}ViewModel.kt"))
}