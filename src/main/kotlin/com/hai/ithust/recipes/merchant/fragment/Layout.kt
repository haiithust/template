package com.hai.ithust.recipes.merchant.fragment

/**
 * @author conghai on 1/28/21.
 */
fun merchantFragmentLayout(): String {
    return """
        <?xml version="1.0" encoding="utf-8"?>
        <androidx.constraintlayout.widget.ConstraintLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            
            <com.sea.foody.nowmerchant.FdSimpleActionBar
                android:id="@+id/action_bar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:layout_constraintTop_toTopOf="parent" />

        </androidx.constraintlayout.widget.ConstraintLayout>
    """.trimIndent()
}