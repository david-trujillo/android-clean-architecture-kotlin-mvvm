package app.example.mvvm.transporte.libs.analytics

import app.example.mvvm.transporte.libs.analytics.event.ScreenViewEvent

interface Analytics {

    fun logView(event: ScreenViewEvent)

}