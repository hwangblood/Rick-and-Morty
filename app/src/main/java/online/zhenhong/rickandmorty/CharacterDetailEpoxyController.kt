package online.zhenhong.rickandmorty

import android.util.Log
import com.airbnb.epoxy.EpoxyController
import online.zhenhong.rickandmorty.databinding.ModelCharacterDetailImageBinding
import online.zhenhong.rickandmorty.databinding.ModelCharacterDetailInfoBinding
import online.zhenhong.rickandmorty.epoxy.EpoxyViewBindingModel
import online.zhenhong.rickandmorty.epoxy.LoadingEpoxyModel
import online.zhenhong.rickandmorty.network.CharacterResponse

class CharacterDetailEpoxyController : EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) requestModelBuild()
        }

    var character: CharacterResponse? = null
        set(value) {
            field = value
            field?.let {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        Log.d("buildModels", character.toString())
        if (isLoading) {
            // show the loading state
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (character == null) {
            // TODO show error state
            Log.d("buildModels", "get a error.")
            return
        }

        // add image model
        ImageEpoxyModel(character!!.image).id("header_image").addTo(this)

        // add info model
        InfoModel(character!!).id("info").addTo(this)

        Log.d("buildModels", "buildModels finished！")

    }

}

class ImageEpoxyModel(val imageUrl: String) :
    EpoxyViewBindingModel<ModelCharacterDetailImageBinding>(R.layout.model_character_detail_image) {
    override fun ModelCharacterDetailImageBinding.bind() {
        headerImage.loadImage(imageUrl)
    }
}

class InfoModel(val character: CharacterResponse) :
    EpoxyViewBindingModel<ModelCharacterDetailInfoBinding>(R.layout.model_character_detail_info) {
    override fun ModelCharacterDetailInfoBinding.bind() {
        nameValue.text = character.name
        statusValue.text = character.status
        statusValue.setStatusColor(character.status)
        genderValue.text = character.gender
        speciesValue.text = character.species
        originValue.text = character.origin.name
        locationValue.text = character.location.name
    }
}