package app.example.mvvm.transporte.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import app.example.mvvm.transporte.initializers.AppInitializer
import app.example.mvvm.transporte.initializers.CacheInitializer
import app.example.mvvm.transporte.libs.analytics.Analytics
import app.example.mvvm.transporte.libs.analytics.FirebaseReporter

@Module
@InstallIn(SingletonComponent::class)
abstract class AppInitializersModule {

    @Binds
    @IntoSet
    abstract fun provideRxCacheInitializer(bind: CacheInitializer): AppInitializer

    @Binds
    abstract fun provideAnalytics(bind: FirebaseReporter): Analytics

}