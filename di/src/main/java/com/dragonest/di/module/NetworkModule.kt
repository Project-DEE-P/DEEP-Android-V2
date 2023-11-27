package com.dragonest.di.module

import com.dragonest.data.network.api.CardApi
import com.dragonest.data.network.api.ClovaOcrApi
import com.dragonest.data.network.api.UserApi
import com.dragonest.data.remote.api.OauthApi
import com.dragonest.di.BASE_CLOVA_URL
import com.dragonest.di.BASE_URL
import com.dragonest.di.BasicRetrofit
import com.dragonest.di.ClovaRetrofit
import com.dragonest.di.HeaderInterceptor
import com.dragonest.di.LoggingInterceptor
import com.dragonest.di.OAUTH_BASE_URL
import com.dragonest.di.OauthRetrofit
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideCardApi(@BasicRetrofit retrofit: Retrofit): CardApi =
        retrofit.create(CardApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(@BasicRetrofit retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideOauthApi(@OauthRetrofit retrofit: Retrofit): OauthApi =
        retrofit.create(OauthApi::class.java)

    @Provides
    @Singleton
    fun provideClovaApi(@ClovaRetrofit retrofit: Retrofit): ClovaOcrApi =
        retrofit.create(ClovaOcrApi::class.java)


    @BasicRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @OauthRetrofit
    @Provides
    @Singleton
    fun provideOauthRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OAUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @ClovaRetrofit
    @Provides
    @Singleton
    fun provideClovaRetrofit(): Retrofit {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(180, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(180, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(180, TimeUnit.SECONDS)
        val okHttpClient = okHttpClientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(BASE_CLOVA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @HeaderInterceptor headerInterceptor: Interceptor,
        @LoggingInterceptor loggerInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(headerInterceptor)
        okHttpClientBuilder.addInterceptor(loggerInterceptor)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    @LoggingInterceptor
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    @HeaderInterceptor
    fun provideHeaderInterceptor() = Interceptor { chain ->
        with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxMSIsImlhdCI6MTcwMDk4MjQzMywiZXhwIjoxNzAxNTg3MjMzLCJpc3MiOiJERUVQIiwic3ViIjoidG9rZW4ifQ.iwjwQk3_Nw_oKhZpjhkjW4D8TpWL9_0vo--Mn9zaT6Q")
//                .addHeader("Authorization","Bearer " + HiltApplication.pref.accessToken)
                .build()
            proceed(newRequest)
        }
    }

}