package io.smallant.wizard.data.sources.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIManager {

    companion object {
        private val ENDPOINT = "http://192.168.1.18:3000/"
    }

    private var privOkHttpClient: OkHttpClient? = null

    val apiWizardService: WizardService by lazy {
        apiWizardRetrofit.create(WizardService::class.java)
    }

    private val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    private val okHttpClient: OkHttpClient
        get() {
            if (privOkHttpClient == null) {
                val builder = OkHttpClient.Builder()

                builder.connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)

                privOkHttpClient = builder.build()
            }
            return privOkHttpClient as OkHttpClient

        }

    private val apiWizardRetrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(ENDPOINT)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}