package app.example.mvvm.transporte

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import app.example.mvvm.transporte.initializers.AppInitializers
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var appInitializers: AppInitializers

    override fun onCreate() {
        super.onCreate()
        appInitializers.init(this)
    }

}