package app.example.mvvm.transporte.features.tramlist.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import app.example.mvvm.transporte.common.network.createNetworkClient
import app.example.mvvm.transporte.features.tramlist.BuildConfig
import app.example.mvvm.transporte.features.tramlist.data.TramListRepository
import app.example.mvvm.transporte.features.tramlist.data.TramListRepositoryImpl
import app.example.mvvm.transporte.features.tramlist.data.remote.TramListRemote

@Module
@InstallIn(ViewModelComponent::class)
abstract class TramListModule {

    @Binds
    abstract fun binsTramRepository(impl: TramListRepositoryImpl): TramListRepository

    companion object {

        @Provides
        fun provideTrolleyRemote(@ApplicationContext context: Context): TramListRemote =
            createNetworkClient(context, "https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/transporte-urbano/", BuildConfig.DEBUG)
                .build()
                .create(TramListRemote::class.java)

    }

}