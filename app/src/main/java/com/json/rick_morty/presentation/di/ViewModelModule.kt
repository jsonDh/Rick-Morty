package com.json.rick_morty.presentation.di

import com.json.rick_morty.domain.repository.CharacterRepository
import com.json.rick_morty.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindCharacterRepository(repository: CharacterRepositoryImpl) : CharacterRepository

}