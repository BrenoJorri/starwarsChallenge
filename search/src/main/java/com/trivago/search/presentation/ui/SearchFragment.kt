package com.trivago.search.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trivago.core.extesion.gone
import com.trivago.core.extesion.visible
import com.trivago.core.result.observeResource
import com.trivago.domain.search.model.SearchDomain
import com.trivago.search.R
import com.trivago.search.core.di.injectModules
import com.trivago.search.core.exception.BadFormatException
import com.trivago.search.databinding.FragmentSearchBinding
import com.trivago.search.presentation.SearchViewModel
import com.trivago.search.presentation.ui.adapter.SearchResultAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.trivago.trivagochallenge.R as RTrivago

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModules()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        initUi()
        setupObservers()
    }

    private fun initUi() = with(binding.searchView) {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { query ->
                    searchViewModel.performSearch(MutableStateFlow(query))
                }
                return true
            }
        })
    }

    private fun setupObservers() {
        searchViewModel.searchLiveData.observeResource(viewLifecycleOwner,
            onSuccess = { data ->
                showSuccess(data)
            },
            onError = { error ->
                showError(error)
            },
            onLoading = {
                showLoading()
            },
            onEmpty = {
                showEmpty()
            })
    }

    private fun showEmpty() {
        binding.searchLoading.gone()
        binding.rvResultSearch.gone()
        binding.emptyText.visible()
    }

    private fun showLoading() {
        binding.emptyText.gone()
        binding.rvResultSearch.gone()
        binding.searchLoading.visible()
    }

    private fun showError(error: Throwable) {
        binding.searchLoading.gone()
        when (error) {
            is BadFormatException -> {
                Toast.makeText(requireContext(), "bad query", Toast.LENGTH_SHORT).show()
            }
            else -> Toast.makeText(requireContext(), "Offline exception $error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSuccess(searchDomain: SearchDomain) {
        binding.searchLoading.gone()
        binding.emptyText.gone()
        with(binding.rvResultSearch) {
            visible()
            adapter = SearchResultAdapter(searchDomain.results) { character ->
                findNavController().navigate(
                    RTrivago.id.action_searchFragment_to_detailFragment,
                    bundleOf("character" to character)
                )
                binding.searchView.clearFocus()
                binding.searchView.setQuery("", false)
            }
        }
    }
}
