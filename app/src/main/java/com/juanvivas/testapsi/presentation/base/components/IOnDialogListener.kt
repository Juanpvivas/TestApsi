package com.juanvivas.testapsi.presentation.base.components

interface IOnDialogListener {
    abstract fun onDialogOkClick(dialogTag: String)
    abstract fun onDialogCancelClick(dialogTag: String)
    abstract fun onLinkClick()
}