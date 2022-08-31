package app.example.mvvm.transporte.features.tramlist.domain.model

import com.squareup.moshi.Json

data class TramListResponse(
    val result: List<Tram>
)

data class Tram(
    val id: String,
    val title: String,
    val lastUpdated: String,
    val geometry: TramGeometry,
    @field:Json(name = "destinos")
    val destinations: List<TramDestination>?,
    val gtfsId: String,
)

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