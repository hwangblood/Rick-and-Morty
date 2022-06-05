package online.zhenhong.rickandmorty.charcter.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import online.zhenhong.rickandmorty.Constants
import online.zhenhong.rickandmorty.databinding.ActivityCharacterDetailBinding

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding

    private val viewModel by viewModels<CharacterDetailViewModel>()

    private val epoxyController by lazy { CharacterDetailEpoxyController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.character.observe(this) {
            epoxyController.character = it
        }

        val characterId = intent.getIntExtra(Constants.INTENT_EXTRA_KEY_CHARACTER_ID, 1)
        viewModel.refreshCharacter(characterId)

        binding.epoxyRecycler.setControllerAndBuildModels(epoxyController)
    }

}
