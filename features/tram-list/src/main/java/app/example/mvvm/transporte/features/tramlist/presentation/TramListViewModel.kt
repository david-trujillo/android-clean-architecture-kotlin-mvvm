package app.example.mvvm.transporte.features.tramlist.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import app.example.mvvm.transporte.common.network.ApiStatus
import app.example.mvvm.transporte.common.views.presentation.viewmodel.BaseViewModel
import app.example.mvvm.transporte.common.views.presentation.viewmodel.BaseViewState
import app.example.mvvm.transporte.features.tramlist.domain.model.Tram
import app.example.mvvm.transporte.features.tramlist.domain.usecase.GetTramListUseCase
import javax.inject.Inject

@HiltViewModel
internal class TramListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getTrolleyList: GetTramListUseCase
) : BaseViewModel<TramListViewModel.ViewState>(ViewState()) {

    internal data class ViewState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val tramList: List<Tram>? = null,
    ) : BaseViewState

    override fun loadData() {
        updateState {
            copy(tramList = null, isError = false, isLoading = true)
        }

        viewModelScope.launch {
            getTrolleyList.execute {
                when (it.status) {
                    ApiStatus.SUCCESS -> updateState {
                        copy(tramList = it.data?.result, isError = false, isLoading = false)
                    }
                    ApiStatus.ERROR -> updateState {
                        copy(tramList = null, isError = true, isLoading = false)
                    }
                }
            }
        }
    }

}