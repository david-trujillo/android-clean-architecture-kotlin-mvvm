package app.example.mvvm.transporte.common.views.domain

import app.example.mvvm.transporte.common.network.ApiResult

abstract class BaseUseCase<Parameters : BaseCaseParameters, Result : ApiResult<*>> {

    lateinit var parameters : Parameters

    abstract suspend fun execute(onResult: (Result) -> Unit)

}