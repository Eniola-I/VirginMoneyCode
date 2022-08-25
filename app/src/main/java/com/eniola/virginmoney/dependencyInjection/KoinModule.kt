package com.eniola.virginmoney.dependencyInjection

import com.eniola.virginmoney.api.OfficeApi
import com.eniola.virginmoney.api.OfficeRepository
import com.eniola.virginmoney.api.OfficeRepositoryImpl
import com.eniola.virginmoney.viewModel.OfficeViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    fun moshi() = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    fun repository(officeApi: OfficeApi) : OfficeRepository = OfficeRepositoryImpl(officeApi)

    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    fun networkApi(okHttpClient: OkHttpClient, moshi: Moshi) =
        Retrofit.Builder()
            .baseUrl(OfficeApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(OfficeApi::class.java)

    single { repository(get())}
    single { moshi()}
    single { loggingInterceptor()}
    single { okHttpClient(get())}
    single { networkApi(get(),get())}
}

val viewModelModule = module {
    viewModel {
        OfficeViewModel(get())
    }
}
