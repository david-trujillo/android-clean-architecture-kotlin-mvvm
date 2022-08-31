package app.example.mvvm.transporte.features.tramlist.data.remote

import app.example.mvvm.transporte.features.tramlist.domain.model.TramListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface TramListRemote {

    @Headers("Accept: application/json", "Cache-Control: max-age=10")
    @GET("parada-tranvia")
    fun list(): Call<TramListResponse>

}