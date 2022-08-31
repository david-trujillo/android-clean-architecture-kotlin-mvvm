package app.example.mvvm.transporte.initializers

import android.app.Application
import app.example.mvvm.transporte.App
import javax.inject.Inject

/**
 * Delegate used to isolate any application initialization.
 * Usage:
 * - Create a class implementing [AppInitializer]
 * - Add a provider in [ApplicationModule]
 *
 * Dagger will inject that class in the [initializers] set and execute it during
 * [Application.onCreate].
 */
class AppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>
) {
    fun init(application: App) {
        initializers.forEach {
            it.init(application)
        }
    }
}
