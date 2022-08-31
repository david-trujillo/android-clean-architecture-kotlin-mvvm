package app.example.mvvm.transporte.features.tram.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import app.example.mvvm.transporte.common.network.createNetworkClient
import app.example.mvvm.transporte.features.tram.BuildConfig
import app.example.mvvm.transporte.features.tram.data.TramRepository
import app.example.mvvm.transporte.features.tram.data.TramRepositoryImpl
import app.example.mvvm.transporte.features.tram.data.remote.TramRemote

@Module
@InstallIn(ViewModelComponent::class)
abstract class TramModule {

    @Binds
    abstract fun binsTramRepository(impl: TramRepositoryImpl): TramRepository

    companion object {

        @Provides
        fun provideTramRemote(@ApplicationContext context: Context): TramRemote =
            createNetworkClient(context, "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/", debug = BuildConfig.DEBUG)
                .build()
                .create(TramRemote::class.java)

    }

}