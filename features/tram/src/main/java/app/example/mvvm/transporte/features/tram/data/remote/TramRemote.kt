package app.example.mvvm.transporte.features.tram.data.remote

import app.example.mvvm.transporte.features.tram.domain.model.TramResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface TramRemote {

    @Headers("Accept: application/json", "Cache-Control: max-age=10")
    @GET("parada-tranvia/{id}")
    fun search(
        @Path("id") id: String?,
    ): Call<TramResponse>

}