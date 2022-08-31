package app.example.mvvm.transporte.initializers

import android.app.Application
import app.example.mvvm.transporte.common.cache.CacheLibrary
import javax.inject.Inject

class CacheInitializer @Inject constructor() : AppInitializer {

    override fun init(application: Application) {
        CacheLibrary.init(application)
    }

}