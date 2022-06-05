package online.zhenhong.rickandmorty.charcter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import online.zhenhong.rickandmorty.databinding.ActivityCharacterListBinding

class CharacterListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterListBinding

    private val viewModel by viewModels<CharactersViewModel>()

    private val epoxyController by lazy { CharacterListPagingEpoxyController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.charactersPagedListLiveData.observe(this) { pagedList ->
            epoxyController.submitList(pagedList)
        }

        binding.epoxyRecycler.setController(epoxyController)
    }
}