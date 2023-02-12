package ru.netology.andad29.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.netology.andad29.db.AppDb
import ru.netology.andad29.repository.PostRepository
import ru.netology.andad29.repository.PostRepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

@HiltWorker
class SavePostWorker @AssistedInject constructor(
    @Assisted appllicationContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: PostRepository
) : CoroutineWorker(appllicationContext, params) {
    companion object {
        const val postKey = "post"
    }

    override suspend fun doWork(): Result {
        val id = inputData.getLong(postKey, 0L)
        if (id == 0L) {
            return Result.failure()
        }
        return try {
            repository.processWork(id)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}