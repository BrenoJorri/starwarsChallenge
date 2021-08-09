package com.trivago.characterdetail.core.di

import com.trivago.characterdetail.presentation.DetailViewModel
import com.trivago.domain.detail.usecase.*
import com.trivago.domain.favorites.usecase.FavoriteCharacterUseCaseImpl
import com.trivago.domain.favorites.usecase.FavoritesCharacterUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectModules() = loadFeatureModules

private val loadFeatureModules by lazy {
    loadKoinModules(searchModule)
}

val searchModule = module {
    factory<CharacterDetailSpeciesUseCase> { CharacterDetailSpeciesUseCaseImpl(get()) }
    factory<CharacterDetailFilmUseCase> { CharacterDetailFilmUseCaseImpl(get()) }
    factory<CharacterDetailHomeWorldUseCase> { CharacterDetailHomeWorldUseCaseImpl(get()) }
    single<FavoritesCharacterUseCase> { FavoriteCharacterUseCaseImpl(get()) }
    viewModel { DetailViewModel(get(), get(), get(), get()) }
}
