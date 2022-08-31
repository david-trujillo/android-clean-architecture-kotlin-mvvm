package app.example.mvvm.transporte.libs.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.qualifiers.ApplicationContext
import app.example.mvvm.transporte.libs.analytics.event.ScreenViewEvent
import javax.inject.Inject

class FirebaseReporter @Inject constructor(
    @ApplicationContext val context: Context
) : Analytics {

    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun logView(event: ScreenViewEvent) {
        firebaseAnalytics.logEvent("screen_view", Bundle(1).apply {
            putString("screen", event.screenName)
        })
    }

}