package app.example.mvvm.transporte.features.tramlist.presentation

import app.example.mvvm.transporte.features.tramlist.domain.model.Tram
import app.example.mvvm.transporte.features.tramlist.domain.model.TramDestination

data class TrolleyItemUI(
    val id: String,
    val title: String,
    var destinations: MutableList<TramDestination>?
)

fun Tram.toUI(): TrolleyItemUI =
    TrolleyItemUI(
        id = id,
        title = title,
        destinations = destinations?.toMutableList()
    )