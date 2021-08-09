package com.trivago.characterdetail.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.trivago.characterdetail.R
import com.trivago.characterdetail.core.di.injectModules
import com.trivago.characterdetail.databinding.FragmentDetailBinding
import com.trivago.characterdetail.presentation.DetailViewModel
import com.trivago.characterdetail.presentation.ui.adapter.FilmsAdapter
import com.trivago.characterdetail.presentation.ui.adapter.SpeciesAdapter
import com.trivago.core.extesion.gone
import com.trivago.core.extesion.visible
import com.trivago.core.result.observeResource
import com.trivago.domain.detail.model.FilmDomain
import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.domain.favorites.model.FavoriteCharacterDomain
import com.trivago.domain.search.model.CharacterDomain
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val detailViewModel: DetailViewModel by viewModel()
    private var characterDomain: CharacterDomain? = null
    private lateinit var navController: NavController
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModules()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        characterDomain = arguments?.getParcelable("character")
        initUi()
        detailViewModel.getDetails(
            characterDomain?.films ?: listOf(),
            characterDomain?.species ?: listOf(),
            characterDomain?.homeworld ?: ""
        )
        setupObservers()
    }

    private fun initUi() = with(binding) {
        characterInfoContainer.characterInfoName.text =
            getString(R.string.character_info_name, characterDomain?.name)
        characterInfoContainer.characterInfoHeight.text = getString(
            R.string.character_info_height,
            characterDomain?.height,
            characterDomain?.heightInches
        )
        characterInfoContainer.characterInfoBirthyear.text =
            getString(R.string.character_info_birthyear, characterDomain?.birthYear)

        val activity = requireActivity() as AppCompatActivity
        detailToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        activity.setSupportActionBar(detailToolbar)

        binding.saveButton.setOnClickListener {
            //FIXME NEED TO CHANGE FOR USE A MAPPER TO FAVORITEDOMAIN
            detailViewModel.saveCharacter(FavoriteCharacterDomain(characterDomain?.name))
        }
    }


    private fun setupObservers() {
        detailViewModel.filmsLiveData.observeResource(viewLifecycleOwner,
            onSuccess = { films -> showFilmsSuccess(films) },
            onError = { showFilmsError() },
            onLoading = { showFilmsLoading() })


        detailViewModel.speciesLiveData.observeResource(viewLifecycleOwner,
            onSuccess = { species -> showSpeciesSuccess(species) },
            onError = { showSpeciesError() },
            onLoading = { showSpeciesLoading() },
            onEmpty = { showSpeciesEmpty() })

        detailViewModel.homeWorldLiveData.observeResource(viewLifecycleOwner,
            onSuccess = { homeWorld -> showHomeWorldSuccess(homeWorld) },
            onError = {
                showHomeWorldError()
            },
            onLoading = { showHomeWorldLoading() })
    }

    private fun showSpeciesEmpty() =
        with(binding.speciesContainer) {
            speciesLoading.gone()
            rvSpecies.gone()
            speciesInfoEmpty.visibility = View.VISIBLE
        }

    private fun showFilmsSuccess(films: List<FilmDomain>) =
        with(binding.filmsContainer) {
            filmsError.root.gone()
            filmLoading.gone()
            rvFilms.visible()
            rvFilms.adapter = FilmsAdapter(films)
        }

    private fun showSpeciesSuccess(species: List<SpeciesDomain>) =
        with(binding.speciesContainer) {
            speciesError.root.gone()
            speciesLoading.gone()
            rvSpecies.visible()
            rvSpecies.adapter = SpeciesAdapter(species)
        }

    private fun showHomeWorldSuccess(homeWorld: HomeWorldDomain) =
        with(binding.planetContainer) {
            planetError.root.gone()
            planetLoading.gone()
            planetGroup.visible()
            planetInfoName.text = getString(R.string.character_info_name, homeWorld.name)
            planetInfoPopulation.text =
                getString(R.string.planet_population, homeWorld.population)
        }

    private fun showHomeWorldError() =
        with(binding.planetContainer) {
            planetLoading.gone()
            planetInfoContainer.gone()
            with(planetError) {
                errorView.visible()
            }
        }

    private fun showHomeWorldLoading() =
        with(binding.planetContainer) {
            planetError.root.gone()
            planetGroup.gone()
            planetLoading.visible()
        }

    private fun showSpeciesError() =
        with(binding.speciesContainer) {
            speciesInfoContainer.gone()
            speciesError.root.visible()
        }

    private fun showFilmsLoading() =
        with(binding.filmsContainer) {
            filmsError.root.gone()
            rvFilms.gone()
            filmLoading.visible()
        }

    private fun showFilmsError() =
        with(binding.filmsContainer) {
            filmsError.root.visible()
            filmsInfoContainer.gone()
        }

    private fun showSpeciesLoading() =
        with(binding.speciesContainer) {
            speciesError.root.gone()
            rvSpecies.gone()
            speciesLoading.visible()
        }
}