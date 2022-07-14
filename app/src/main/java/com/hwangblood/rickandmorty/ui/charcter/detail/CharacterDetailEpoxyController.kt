package com.hwangblood.rickandmorty.ui.charcter.detail

import android.util.Log
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.hwangblood.rickandmorty.R
import com.hwangblood.rickandmorty.databinding.ModelCharacterDetailImageBinding
import com.hwangblood.rickandmorty.databinding.ModelCharacterDetailInfoBinding
import com.hwangblood.rickandmorty.databinding.ModelEpisodeCarouselItemBinding
import com.hwangblood.rickandmorty.databinding.ModelEpisodeHeaderBinding
import com.hwangblood.rickandmorty.domain.models.Character
import com.hwangblood.rickandmorty.domain.models.Episode
import com.hwangblood.rickandmorty.epoxy.EpoxyViewBindingModel
import com.hwangblood.rickandmorty.epoxy.LoadingEpoxyModel
import com.hwangblood.rickandmorty.loadImage
import com.hwangblood.rickandmorty.setStatusColor

class CharacterDetailEpoxyController : EpoxyController() {

    private var isLoading: Boolean = true
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

class ImageEpoxyModel(private val imageUrl: String) :
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