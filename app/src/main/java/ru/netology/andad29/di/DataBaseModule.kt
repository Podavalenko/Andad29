package ru.netology.andad29.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.netology.andad29.dao.PostDao
import ru.netology.andad29.dao.PostRemoteKeyDao
import ru.netology.andad29.dao.PostWorkDao
import ru.netology.andad29.db.AppDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDb =
        Room.databaseBuilder(context, AppDb::class.java, "app.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePostDao(db:AppDb): PostDao = db.postDao()

    @Provides
    fun providePostWorkDao(db:AppDb): PostWorkDao = db.postWorkDao()

    @Provides
    fun providePostRemoteKeyDao(db: AppDb): PostRemoteKeyDao = db.postRemoteKeyDao()
}