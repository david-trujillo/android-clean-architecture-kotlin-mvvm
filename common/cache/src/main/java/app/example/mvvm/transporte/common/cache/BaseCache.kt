package app.example.mvvm.transporte.common.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


abstract class BaseCache<T> {

    abstract var key: String?

    fun store(response: T): Single<T> = RxPaperBook.with().write(key!!, response).toSingleDefault(response)

    fun read(): Single<T> = RxPaperBook.with().read(key!!)

    fun delete(): Completable = RxPaperBook.with().delete(key!!)

    fun lastModified(): Single<Long> = RxPaperBook.with().lastModified(key!!)

    open fun isExpired(): Single<Boolean> = Single.fromObservable(Observable.just(false))

}