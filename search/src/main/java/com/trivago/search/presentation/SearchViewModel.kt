package com.trivago.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.trivago.core.base.BaseViewModel
import com.trivago.core.result.Resource
import com.trivago.domain.search.model.SearchDomain
import com.trivago.domain.search.usecase.SearchUseCase
import com.trivago.search.core.exception.BadFormatException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(private val useCase: SearchUseCase) : BaseViewModel() {

    private val _searchMutableLiveData = MutableLiveData<Resource<SearchDomain>>()

    val searchLiveData: LiveData<Resource<SearchDomain>> by lazy {
        _searchMutableLiveData
    }

    fun performSearch(query: MutableStateFlow<String>) {
        startSearch(query)
    }

    private fun startSearch(query: StateFlow<String>) {
        viewModelScope.launch {
            query
                .debounce(600)
                .filter { it.length > 2 }
                .distinctUntilChanged()
                .flatMapLatest { finalQuery ->
                    useCase.search(finalQuery)
                        .onStart { _searchMutableLiveData.loading() }
                        .catch { e -> _searchMutableLiveData.error(e) }
                }.collect { searchDomain ->
                    searchDomain?.let { search ->
                        if (search.isValid()) {
                            _searchMutableLiveData.success(search)
                        } else {
                            _searchMutableLiveData.empty()
                        }
                    } ?: _searchMutableLiveData.error(BadFormatException())
                }
        }
    }
}