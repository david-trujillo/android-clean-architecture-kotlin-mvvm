package app.example.mvvm.transporte.features.tramlist.data.local

import app.example.mvvm.transporte.common.cache.BaseCache
import app.example.mvvm.transporte.common.views.presentation.extension.minus
import app.example.mvvm.transporte.common.views.presentation.extension.toDay
import app.example.mvvm.transporte.common.views.presentation.extension.today
import app.example.mvvm.transporte.features.tramlist.domain.model.TramListResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TramListLocal @Inject constructor() : BaseCache<TramListResponse>() {

    companion object {
        private const val TRAM_LIST_KEY = "tram-list-response"
        private const val TRAM_LIST_EXPIRATION_TIME = 10
    }

    override var key: String? = TRAM_LIST_KEY

    override fun isExpired(): Single<Boolean> {
        return lastModified().map {
            val time = today().minus(TRAM_LIST_EXPIRATION_TIME.toDay()).time
            it < time
        }
    }

}