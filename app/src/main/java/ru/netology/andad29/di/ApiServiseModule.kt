package ru.netology.andad29.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.netology.andad29.api.*
import ru.netology.andad29.auth.AppAuth
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiServiseModule {

    @Singleton
    @Provides
    fun provideApiServise(auth: AppAuth): ApiService {
        return retrofit(okhttp(loggingInterceptor(), authInterceptor(auth)))
            .create(ApiService::class.java)
    }

}