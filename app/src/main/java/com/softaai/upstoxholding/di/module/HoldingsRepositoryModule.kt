package com.softaai.upstoxholding.di.module

import com.softaai.upstoxholding.repository.DefaultHoldingsRepository
import com.softaai.upstoxholding.repository.HoldingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class HoldingsRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindHoldingsRepository(holdingsRepository: DefaultHoldingsRepository): HoldingsRepository
}