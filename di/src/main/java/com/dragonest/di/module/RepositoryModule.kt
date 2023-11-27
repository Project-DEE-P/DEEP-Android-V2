package com.dragonest.di.module

import com.dragonest.data.repositoryimpl.CardRepositoryImpl
import com.dragonest.data.repositoryimpl.UserRepositoryImpl
import com.dragonest.di.BasicRetrofit
import com.dragonest.domain.repository.CardRepository
import com.dragonest.domain.repository.UserRepository
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

   @Provides
   @Singleton
   fun provideCardRepository(
        impl : CardRepositoryImpl
    ): CardRepository = impl

    @Provides
    @Singleton
    fun provideUserRepository(
        impl : UserRepositoryImpl
    ): UserRepository = impl

//    @Provides
//    @Singleton
//    fun provideClovaOcrRepository(
//        impl : ClovaOcrRepositoryImpl
//    ) : ClovaOcrRepository = impl

}
