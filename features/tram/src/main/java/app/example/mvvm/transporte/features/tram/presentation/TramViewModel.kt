package app.example.mvvm.transporte.features.tram.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import app.example.mvvm.transporte.common.network.ApiResult
import app.example.mvvm.transporte.common.views.presentation.viewmodel.BaseViewModel
import app.example.mvvm.transporte.common.views.presentation.viewmodel.BaseViewState
import app.example.mvvm.transporte.features.tram.domain.model.TramResponse
import app.example.mvvm.transporte.features.tram.domain.usecase.GetTram
import javax.inject.Inject

@HiltViewModel
class TramViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getTram: GetTram
) : BaseViewModel<TramViewModel.ViewState>(ViewState()) {

    data class ViewState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val data: TramResponse? = null,
        val stopID: String? = null,
    ) : BaseViewState


    fun loadTramInfo(id: String) {
        updateState { copy(stopID = id, isLoading = true, isError = false, data = null) }

        viewModelScope.launch {
            getTram.parameters = GetTram.Parameters(id)
            getTram.execute {
                when (it) {
                    is ApiResult.Error -> updateState { copy(isLoading = false, isError = true, data = null) }
                    is ApiResult.Success -> updateState { copy(isLoading = false, isError = false, data = it.data) }
                }
            }
        }
    }

}