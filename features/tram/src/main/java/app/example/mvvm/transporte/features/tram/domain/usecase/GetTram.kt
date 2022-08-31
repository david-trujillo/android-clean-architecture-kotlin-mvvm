package app.example.mvvm.transporte.features.tram.domain.usecase

import app.example.mvvm.transporte.common.network.ApiResult
import app.example.mvvm.transporte.common.views.domain.BaseCaseParameters
import app.example.mvvm.transporte.common.views.domain.BaseUseCase
import app.example.mvvm.transporte.features.tram.domain.model.TramResponse
import app.example.mvvm.transporte.features.tram.data.TramRepository
import javax.inject.Inject

class GetTram @Inject constructor(
    private val repository: TramRepository
) : BaseUseCase<GetTram.Parameters, ApiResult<TramResponse>>() {

    data class Parameters(
        val id: String,
    ) : BaseCaseParameters

    override suspend fun execute(onResult: (ApiResult<TramResponse>) -> Unit) {
        repository.search(parameters.id, onResult)
    }


}