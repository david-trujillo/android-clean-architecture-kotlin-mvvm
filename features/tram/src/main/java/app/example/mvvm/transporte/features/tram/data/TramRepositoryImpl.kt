package app.example.mvvm.transporte.features.tram.data

import app.example.mvvm.transporte.common.network.ApiResult
import app.example.mvvm.transporte.common.views.data.BaseRepository
import app.example.mvvm.transporte.features.tram.data.remote.TramRemote
import app.example.mvvm.transporte.features.tram.domain.model.TramResponse
import javax.inject.Inject

class TramRepositoryImpl @Inject constructor(
    private val remote: TramRemote
) : BaseRepository(), TramRepository {

    override fun search(id: String, onResponse: (ApiResult<TramResponse>) -> Unit) {
        executeRemote(remote.search(id), onResponse)
    }

}