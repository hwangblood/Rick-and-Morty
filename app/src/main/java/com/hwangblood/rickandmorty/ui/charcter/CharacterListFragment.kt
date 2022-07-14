package com.hwangblood.rickandmorty.ui.charcter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hwangblood.rickandmorty.databinding.FragmentCharacterListBinding

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding
        get() = _binding!!

    private val viewModel by viewModels<CharactersViewModel>()

    private val epoxyController by lazy { CharacterListPagingEpoxyController(::onCharacterClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.charactersPagedListLiveData.observe(viewLifecycleOwner) { pagedList ->
            epoxyController.submitList(pagedList)
        }

        binding.epoxyRecycler.setController(epoxyController)
    }

    private fun onCharacterClick(characterId: Int) {
        findNavController().navigate(
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                characterId = characterId
            )
        )
    }
}
