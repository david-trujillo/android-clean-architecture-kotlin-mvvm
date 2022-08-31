package app.example.mvvm.transporte.common.views.data

import app.example.mvvm.transporte.common.cache.BaseCache
import app.example.mvvm.transporte.common.network.ApiResult
import app.example.mvvm.transporte.common.network.make
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call

abstract class BaseRepository {

    fun <T> executeRemote(remote: Call<T>, onResult: (ApiResult<T>) -> Unit) {
        remote.make(onResult)
    }

    fun <T> executeRemoteThenCache(remote: Call<T>, local: BaseCache<T>, onResult: (ApiResult<T>) -> Unit) {
        executeRemote(remote) { result ->
            if (result is ApiResult.Success) {
                result.data?.let { response ->
                    local.store(response)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { t1, t2 -> onResult(result) }
                }
            } else {
                onResult(result)
            }
        }
    }

    fun <T> executeCacheOrRemote(remote: Call<T>, local: BaseCache<T>, onResult: (ApiResult<T>) -> Unit) {
        local.isExpired()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it == true) {
                    executeRemoteThenCache(remote, local, onResult)
                } else {
                    local.read()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            onResult(ApiResult.Success(it))
                        }, {
                            executeRemoteThenCache(remote, local, onResult)
                        })
                }
            }, {
                executeRemoteThenCache(remote, local, onResult)
            })
    }

    fun <T> executeCacheOrRemote(remote: Call<T>, local: BaseCache<T>, preCondition: (T?) -> Boolean, onResult: (ApiResult<T>) -> Unit) {
        executeCacheOrRemote(remote, local) { response ->
            if (preCondition.invoke(response.data)) {
                onResult(response)
            } else {
                executeRemoteThenCache(remote, local, onResult)
            }
        }
    }

}