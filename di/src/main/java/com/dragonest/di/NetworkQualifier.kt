package com.dragonest.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasicRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ClovaRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OauthRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HeaderInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpNoHeaderClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor
