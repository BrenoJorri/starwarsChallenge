package com.trivago.characterdetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.trivago.core.base.BaseViewModel
import com.trivago.core.result.Resource
import com.trivago.domain.detail.model.FilmDomain
import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.domain.detail.usecase.CharacterDetailFilmUseCase
import com.trivago.domain.detail.usecase.CharacterDetailHomeWorldUseCase
import com.trivago.domain.detail.usecase.CharacterDetailSpeciesUseCase
import com.trivago.domain.favorites.model.FavoriteCharacterDomain
import com.trivago.domain.favorites.usecase.FavoritesCharacterUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val speciesUseCase: CharacterDetailSpeciesUseCase,
    private val filmUseCase: CharacterDetailFilmUseCase,
    private val homeWorldUseCase: CharacterDetailHomeWorldUseCase,
    private val favoritesCharacterUseCase: FavoritesCharacterUseCase
) : BaseViewModel() {

    private val _homeWorldMutableLiveData = MutableLiveData<Resource<HomeWorldDomain>>()

    val homeWorldLiveData: LiveData<Resource<HomeWorldDomain>> by lazy {
        _homeWorldMutableLiveData
    }

    private val _speciesMutableLiveData = MutableLiveData<Resource<List<SpeciesDomain>>>()

    val speciesLiveData: LiveData<Resource<List<SpeciesDomain>>> by lazy {
        _speciesMutableLiveData
    }

    private val _filmsMutableLiveData = MutableLiveData<Resource<List<FilmDomain>>>()

    val filmsLiveData: LiveData<Resource<List<FilmDomain>>> by lazy {
        _filmsMutableLiveData
    }

    fun getDetails(films: List<String>, species: List<String>, homeWorld: String) {
        getFilms(films)
        getSpecies(species)
        getHomeWorld(homeWorld)
    }

    internal fun getFilms(films: List<String>) {
        _filmsMutableLiveData.loading()
        viewModelScope.launch {
            try {
                val filmListFlow: List<FilmDomain> = films.mapNotNull { url ->
                    filmUseCase
                        .getFilms(url)
                        .first()
                }

                _filmsMutableLiveData.success(filmListFlow)

            } catch (e: Exception) {
                _filmsMutableLiveData.error(e)
            }
        }
    }

    internal fun getSpecies(species: List<String>) {
        _speciesMutableLiveData.loading()
        viewModelScope.launch {
            try {
                val speciesListFlow: List<SpeciesDomain> = species.mapNotNull { url ->
                    speciesUseCase
                        .getSpecies(url)
                        .firstOrNull()
                }

                if (speciesListFlow.isEmpty().not()) {
                    _speciesMutableLiveData.success(speciesListFlow)
                } else {
                    _speciesMutableLiveData.empty(listOf())
                }


            } catch (e: Exception) {
                _speciesMutableLiveData.error(e)
            }
        }
    }

    internal fun getHomeWorld(homeWorld: String) {
        viewModelScope.launch {
            homeWorldUseCase.getHomeWorld(homeWorld)
                .onStart { _homeWorldMutableLiveData.loading() }
                .catch { _homeWorldMutableLiveData.error(it) }
                .collect { homeWorldDomain ->
                    _homeWorldMutableLiveData.success(homeWorldDomain)
                }
        }
    }

    internal fun saveCharacter(domain: FavoriteCharacterDomain) {
        viewModelScope.launch {
            favoritesCharacterUseCase.saveCharacter(domain)
        }
    }
}
