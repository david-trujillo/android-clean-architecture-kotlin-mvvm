package app.example.mvvm.transporte.features.tramlist.data

import app.example.mvvm.transporte.common.network.ApiResult
import app.example.mvvm.transporte.features.tramlist.domain.model.TramListResponse

interface TramListRepository {

    fun list(onResult: (ApiResult<TramListResponse>) -> Unit)

}