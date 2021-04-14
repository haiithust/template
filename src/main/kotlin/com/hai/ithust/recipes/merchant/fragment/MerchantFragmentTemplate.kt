package com.hai.ithust.recipes.merchant.fragment

import com.android.tools.idea.wizard.template.*
import java.io.File

object MerchantFragmentTemplate : Template {
    private val screenName = stringParameter {
        name = "Screen Name"
        default = "ScreenName"
        help = "The name of the module to create"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY, Constraint.CLASS)
    }

    private val directoryName = stringParameter {
        name = "Directory Name"
        default = "directory"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY, Constraint.PACKAGE)
    }

    override val category: Category
        get() = Category.Fragment

    override val constraints: Collection<TemplateConstraint>
        get() = listOf(TemplateConstraint.AndroidX, TemplateConstraint.Kotlin)

    override val description: String
        get() = "Creates a new Merchant Fragment"

    override val documentationUrl: String?
        get() = null

    override val formFactor: FormFactor
        get() = FormFactor.Mobile

    override val minCompileSdk: Int
        get() = 21

    override val minSdk: Int
        get() = 21

    override val name: String
        get() = "Merchant Fragment"

    override val recipe: Recipe
        get() = {
            merchantFragment(
                it as ModuleTemplateData,
                screenName.value.capitalize(),
                directoryName.value.toLowerCase()
            )
        }

    override val revision: Int
        get() = 1

    override val uiContexts: Collection<WizardUiContext>
        get() = listOf(WizardUiContext.MenuEntry)

    override val widgets: Collection<Widget<*>>
        get() = listOf(
            TextFieldWidget(screenName),
            TextFieldWidget(directoryName)
        )

    override fun thumb(): Thumb {
        return Thumb { findResource(this.javaClass, File("thumbs/merchant_screen.png")) }
    }
}