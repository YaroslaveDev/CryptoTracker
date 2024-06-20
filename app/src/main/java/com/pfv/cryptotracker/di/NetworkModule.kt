package com.pfv.cryptotracker.di

import android.util.Config.DEBUG
import android.util.Log
import com.google.gson.GsonBuilder
import com.pfv.cryptotracker.data.constants.GlobalAppConstants.BASE_URL
import com.pfv.cryptotracker.data.remote.network.NetworkResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val loggingInterceptor = prepareLoggingInterceptor()

        val builder = OkHttpClient.Builder()

        builder.apply {
            callTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)

            addNetworkInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = chain.request().newBuilder()
                requestBuilder
                    .header("Accept", "application/json")
                    .method(original.method, original.body)

                return@addNetworkInterceptor chain.proceed(requestBuilder.build())
            }
            if (DEBUG) {
                addInterceptor(prepareLoggingInterceptor())
            }
        }
        builder.addInterceptor(loggingInterceptor)

        return builder.build()
    }

    private fun prepareLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            private fun print(m: String) {
                Log.i("ApiFactory", "\n$m")
            }

            override fun log(message: String) {
                if (message.startsWith("{") || message.startsWith("[")) try {
                    JSONObject(message).toString(4).also(::print)
                } catch (e: JSONException) {
                    print(message)
                }
                else print(message)
            }
        }).also { it.level = HttpLoggingInterceptor.Level.BODY }
    }
}

private const val DEFAULT_TIMEOUT = 30
const val CONNECT_TIMEOUT = DEFAULT_TIMEOUT
const val WRITE_TIMEOUT = DEFAULT_TIMEOUT
const val READ_TIMEOUT = DEFAULT_TIMEOUT