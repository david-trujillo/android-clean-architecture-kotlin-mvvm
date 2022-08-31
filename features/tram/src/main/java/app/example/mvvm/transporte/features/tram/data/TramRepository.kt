package app.example.mvvm.transporte.features.tram.data

import app.example.mvvm.transporte.common.network.ApiResult
import app.example.mvvm.transporte.features.tram.domain.model.TramResponse

interface TramRepository {

    fun search(id: String, onResponse: (ApiResult<TramResponse>) -> Unit)

}