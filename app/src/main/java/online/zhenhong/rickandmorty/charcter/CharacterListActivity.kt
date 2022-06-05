package online.zhenhong.rickandmorty.charcter

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import online.zhenhong.rickandmorty.Constants
import online.zhenhong.rickandmorty.charcter.detail.CharacterDetailActivity
import online.zhenhong.rickandmorty.databinding.ActivityCharacterListBinding

class CharacterListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterListBinding

    private val viewModel by viewModels<CharactersViewModel>()

    private val epoxyController by lazy { CharacterListPagingEpoxyController(::onCharacterClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.charactersPagedListLiveData.observe(this) { pagedList ->
            epoxyController.submitList(pagedList)
        }

        binding.epoxyRecycler.setController(epoxyController)
    }

    private fun onCharacterClick(characterId: Int) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(Constants.INTENT_EXTRA_KEY_CHARACTER_ID, characterId)
        startActivity(intent)
    }
}