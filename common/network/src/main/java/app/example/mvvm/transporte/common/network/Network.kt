package app.example.mvvm.transporte.common.network

import android.content.Context
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


private const val CACHE_CONTROL_HEADER = "Cache-Control"
private const val CACHE_CONTROL_NO_CACHE = "no-cache"
private const val CACHE_SIZE = 5 * 1024 * 1024L

fun createNetworkClient(context: Context, baseUrl: String, debug: Boolean = false): Retrofit.Builder =
    retrofitClient(baseUrl, httpClient(context, debug))

private fun httpClient(context: Context, debug: Boolean): OkHttpClient {
    val cache = Cache(File(context.cacheDir, "http_cache"), CACHE_SIZE)
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.MINUTES)
        .readTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor(CacheInterceptor())
        .cache(cache)

    if (debug) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit.Builder =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val originalResponse = chain.proceed(request)

        val shouldUseCache = request.header(CACHE_CONTROL_HEADER) != CACHE_CONTROL_NO_CACHE
        if (!shouldUseCache) return originalResponse

        val cacheControl = CacheControl.Builder()
            .maxAge(5, TimeUnit.MINUTES)
            .build()

        return originalResponse.newBuilder()
            .header(CACHE_CONTROL_HEADER, cacheControl.toString())
            .build()
    }

}