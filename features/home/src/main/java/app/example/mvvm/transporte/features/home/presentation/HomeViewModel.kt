package app.example.mvvm.transporte.features.home.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import app.example.mvvm.transporte.common.views.presentation.viewmodel.BaseViewModel
import app.example.mvvm.transporte.common.views.presentation.viewmodel.BaseViewState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeViewModel.State>(State()) {

    data class State(
        val isLoading: Boolean = true
    ) : BaseViewState

}