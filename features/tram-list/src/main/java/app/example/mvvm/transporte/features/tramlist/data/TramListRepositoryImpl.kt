package app.example.mvvm.transporte.features.tramlist.data

import app.example.mvvm.transporte.common.network.ApiResult
import app.example.mvvm.transporte.common.views.data.BaseRepository
import app.example.mvvm.transporte.features.tramlist.data.local.TramListLocal
import app.example.mvvm.transporte.features.tramlist.data.remote.TramListRemote
import app.example.mvvm.transporte.features.tramlist.domain.model.TramListResponse
import javax.inject.Inject

class TramListRepositoryImpl @Inject constructor(
    private val remote: TramListRemote,
    private val local: TramListLocal
) : BaseRepository(), TramListRepository {

    override fun list(onResult: (ApiResult<TramListResponse>) -> Unit) {
        val call = remote.list()
        executeCacheOrRemote(call, local, onResult)
    }

}