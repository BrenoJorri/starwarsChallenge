package com.trivago.search.core.di

import com.trivago.domain.search.usecase.SearchUseCase
import com.trivago.domain.search.usecase.SearchUseCaseImpl
import com.trivago.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectModules() = loadFeatureModules

private val loadFeatureModules by lazy {
    loadKoinModules(searchModule)
}

val searchModule = module {
    single<SearchUseCase> { SearchUseCaseImpl(get()) }
    viewModel { SearchViewModel(get()) }
}

