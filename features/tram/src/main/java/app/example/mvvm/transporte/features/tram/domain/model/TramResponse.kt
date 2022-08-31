package app.example.mvvm.transporte.features.tram.domain.model

import com.squareup.moshi.Json

data class TramResponse(
    val id: String,
    val title: String,
    val lastUpdated: String,
    val geometry: TramGeometry,
    @field:Json(name = "destinos")
    val destinations: List<TramDestination>?,
    val gtfsId: String,
) {

    fun getDestiny(): String? = destinations?.firstOrNull()?.direction

}

data class TramGeometry(
    val type: String,
)

data class TramDestination(
    @field:Json(name = "linea")
    val line: String,
    @field:Json(name = "destino")
    val direction: String,
    @field:Json(name = "minutos")
    val minutes: Int
)