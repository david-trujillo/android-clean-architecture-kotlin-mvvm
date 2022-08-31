package app.example.mvvm.transporte.common.views.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<ViewState : BaseViewState>(
    initialState: ViewState
) : ViewModel() {

    val TAG: String = this::class.simpleName.toString()

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<ViewState> = _uiState

    protected fun updateState(reducer: ViewState.() -> ViewState) {
        _uiState.update(reducer)
    }

    open fun loadData() {

    }

    open fun onViewReady() {

    }

    /* Logger function  */

    open fun logger(msg: String) {
        Log.d(TAG, msg)
    }

}