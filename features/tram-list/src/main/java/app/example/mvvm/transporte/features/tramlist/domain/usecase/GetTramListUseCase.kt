package app.example.mvvm.transporte.features.tramlist.domain.usecase

import app.example.mvvm.transporte.common.network.ApiResult
import app.example.mvvm.transporte.common.views.domain.BaseCaseParameters
import app.example.mvvm.transporte.common.views.domain.BaseUseCase
import app.example.mvvm.transporte.features.tramlist.data.TramListRepository
import app.example.mvvm.transporte.features.tramlist.domain.model.TramListResponse
import javax.inject.Inject

class GetTramListUseCase @Inject constructor(
    private val trolleyRepository: TramListRepository
) : BaseUseCase<BaseCaseParameters, ApiResult<TramListResponse>>() {

    override suspend fun execute(onResult: (ApiResult<TramListResponse>) -> Unit) {
        trolleyRepository.list(onResult)
    }

}