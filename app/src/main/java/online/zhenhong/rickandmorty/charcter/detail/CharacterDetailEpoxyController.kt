package online.zhenhong.rickandmorty.charcter.detail

import android.util.Log
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import online.zhenhong.rickandmorty.R
import online.zhenhong.rickandmorty.databinding.ModelCharacterDetailImageBinding
import online.zhenhong.rickandmorty.databinding.ModelCharacterDetailInfoBinding
import online.zhenhong.rickandmorty.databinding.ModelEpisodeCarouselItemBinding
import online.zhenhong.rickandmorty.databinding.ModelEpisodeHeaderBinding
import online.zhenhong.rickandmorty.domain.models.Character
import online.zhenhong.rickandmorty.domain.models.Episode
import online.zhenhong.rickandmorty.epoxy.EpoxyViewBindingModel
import online.zhenhong.rickandmorty.epoxy.LoadingEpoxyModel
import online.zhenhong.rickandmorty.loadImage
import online.zhenhong.rickandmorty.setStatusColor

class CharacterDetailEpoxyController : EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) requestModelBuild()
        }

    var character: Character? = null
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
        InfoEpoxyModel(character!!).id("info").addTo(this)

        // Episode list header
        EpisodeHeaderModel("Episodes").id("episode_header").addTo(this)

        // Episode horizontal list
        if (character!!.episodeList.isNotEmpty()) {
            val items = character!!.episodeList.map { EpisodeCarouselItemModel(it).id(it.id) }
            CarouselModel_().id("episode_carousel")
                .models(items)
                .numViewsToShowOnScreen(1.3f)
                .addTo(this)
        }

        Log.d("buildModels", "buildModels finished!")
        Log.d("buildModels", "episodeList.size = ${character!!.episodeList.size}")

    }

}

class ImageEpoxyModel(val imageUrl: String) :
    EpoxyViewBindingModel<ModelCharacterDetailImageBinding>(R.layout.model_character_detail_image) {
    override fun ModelCharacterDetailImageBinding.bind() {
        headerImage.loadImage(imageUrl)
    }
}

class InfoEpoxyModel(val character: Character) :
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

class EpisodeCarouselItemModel(private val episode: Episode) :
    EpoxyViewBindingModel<ModelEpisodeCarouselItemBinding>(R.layout.model_episode_carousel_item) {
    override fun ModelEpisodeCarouselItemBinding.bind() {
        episodeText.text = episode.episode
        episodeDetails.text = "${episode.name}\n${episode.airDate}"
    }

}

class EpisodeHeaderModel(private val title: String) :
    EpoxyViewBindingModel<ModelEpisodeHeaderBinding>(R.layout.model_episode_header) {
    override fun ModelEpisodeHeaderBinding.bind() {
        episodeTitle.text = title
    }
}