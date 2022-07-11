package online.zhenhong.rickandmorty.charcter.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import online.zhenhong.rickandmorty.databinding.FragmentCharacterDetailBinding


class CharacterDetailFragment : Fragment() {
    private var _binding: FragmentCharacterDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<CharacterDetailViewModel>()

    private val epoxyController by lazy { CharacterDetailEpoxyController() }

    private val safeArgs: CharacterDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.character.observe(viewLifecycleOwner) {
            epoxyController.character = it
            if (it == null) {
                Toast.makeText(requireActivity(), "Unsuccessful network call!", Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }
        }

        viewModel.refreshCharacter(characterId = safeArgs.characterId)

        binding.epoxyRecycler.setControllerAndBuildModels(epoxyController)
    }
}