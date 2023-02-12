package ru.netology.andad29.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.netology.andad29.dao.PostDao
import ru.netology.andad29.dao.PostRemoteKeyDao
import ru.netology.andad29.dao.PostWorkDao
import ru.netology.andad29.entity.PostEntity
import ru.netology.andad29.entity.PostRemoteKeyEntity
import ru.netology.andad29.entity.PostWorkEntity

@Database(entities = [PostEntity::class, PostWorkEntity::class, PostRemoteKeyEntity::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun postWorkDao(): PostWorkDao
    abstract fun postRemoteKeyDao(): PostRemoteKeyDao

}

