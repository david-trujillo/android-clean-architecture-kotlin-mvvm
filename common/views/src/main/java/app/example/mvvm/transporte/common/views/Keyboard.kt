package app.example.mvvm.transporte.common.views

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


object Keyboard {

    fun show(v: View) {
        if (v.requestFocus()) {
            val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hide(v: View) {
        v.requestFocus()
        (v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
