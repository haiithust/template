package com.hai.ithust.recipes

import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.hai.ithust.recipes.core.fragment.HiltViewModelFragmentTemplate
import com.hai.ithust.recipes.merchant.fragment.MerchantFragmentTemplate
import com.hai.ithust.recipes.core.list.multiple.MultipleViewAdapterTemplate

class CustomWizardTemplateProvider : WizardTemplateProvider() {
    override fun getTemplates() =
        listOf(
            MerchantFragmentTemplate,
            MultipleViewAdapterTemplate,
            HiltViewModelFragmentTemplate
        )
}