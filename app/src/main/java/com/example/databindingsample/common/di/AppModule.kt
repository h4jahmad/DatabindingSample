package com.example.databindingsample.common.di

import com.example.databindingsample.BuildConfig
import com.example.databindingsample.common.data.local.UserDataSource
import com.example.databindingsample.common.data.local.UserLocalDataSourceImpl
import com.example.databindingsample.common.data.remote.NetworkResponseAdapterFactory
import com.example.databindingsample.common.data.remote.PixaBayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDataProvider(): UserDataSource = UserLocalDataSourceImpl()

    @Provides
    fun provideImageService(
        retrofit: Retrofit,
    ): PixaBayService = retrofit.create(PixaBayService::class.java)

    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
    }.build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideCallAdapterFactory(): NetworkResponseAdapterFactory = NetworkResponseAdapterFactory()

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        callAdapterFactory: NetworkResponseAdapterFactory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.PIXABAY_BASE_URL)
        .client(client)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()

}