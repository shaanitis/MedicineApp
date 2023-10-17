package com.example.materialcompose.dependencyInjection

import com.example.materialcompose.viewModel.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    fun getAppRepository(): AppRepository {
        return AppRepository()
    }
}