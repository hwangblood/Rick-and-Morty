package online.zhenhong.rickandmorty

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import online.zhenhong.rickandmorty.databinding.LayoutMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: LayoutMainBinding

    private val viewModel by viewModels<SharedViewModel>()

    private val epoxyController by lazy { CharacterDetailEpoxyController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.character.observe(this) {
            epoxyController.character = it
        }

        viewModel.refreshCharacter(21)

        binding.epoxyRecycler.setControllerAndBuildModels(epoxyController)
    }

}