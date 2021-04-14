package com.hai.ithust.recipes.core.list.multiple

import com.android.tools.idea.wizard.template.*
import java.io.File

object MultipleViewAdapterTemplate : Template {
    private val adapterName = stringParameter {
        name = "Adapter Name"
        default = "Feature"
        help = "The name of the module to create"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY, Constraint.CLASS)
    }

    private val holderName = stringParameter {
        name = "Holder Names"
        default = "Feature"
        help = "The name of holders, separate by ,"
        constraints = listOf(Constraint.NONEMPTY)
    }

    override val category: Category
        get() = Category.Other

    override val constraints: Collection<TemplateConstraint>
        get() = listOf(TemplateConstraint.AndroidX, TemplateConstraint.Kotlin)

    override val description: String
        get() = "Creates a new Merchant Recycler Adapter"

    override val documentationUrl: String?
        get() = null

    override val formFactor: FormFactor
        get() = FormFactor.Mobile

    override val minCompileSdk: Int
        get() = 21

    override val minSdk: Int
        get() = 21

    override val name: String
        get() = "Merchant Recycler Adapter"

    override val recipe: Recipe
        get() = {
            multipleViewAdapter(
                it as ModuleTemplateData,
                adapterName.value.capitalize(),
                holderName.value
            )
        }

    override val revision: Int
        get() = 1

    override val uiContexts: Collection<WizardUiContext>
        get() = listOf(WizardUiContext.MenuEntry)

    override val widgets: Collection<Widget<*>>
        get() = listOf(
            TextFieldWidget(adapterName),
            TextFieldWidget(holderName)
        )

    override fun thumb(): Thumb {
        return Thumb { findResource(this.javaClass, File("thumbs/merchant_screen.png")) }
    }
}