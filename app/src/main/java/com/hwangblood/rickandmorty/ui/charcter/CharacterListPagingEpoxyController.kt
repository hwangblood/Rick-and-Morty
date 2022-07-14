package com.hwangblood.rickandmorty.ui.charcter

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.hwangblood.rickandmorty.R
import com.hwangblood.rickandmorty.databinding.CharacterListHeaderTitleBinding
import com.hwangblood.rickandmorty.databinding.CharacterListItemBinding
import com.hwangblood.rickandmorty.epoxy.EpoxyViewBindingModel
import com.hwangblood.rickandmorty.loadImage
import com.hwangblood.rickandmorty.network.models.CharacterResponse
import java.util.*

class CharacterListPagingEpoxyController(
    private val onCharacterClick: (Int) -> Unit
) : PagedListEpoxyController<CharacterResponse>() {
    override fun buildItemModel(currentPosition: Int, item: CharacterResponse?): EpoxyModel<*> {
        return CharacterListItemEpoxyModel(item!!, onCharacterClick).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        if (models.isEmpty()) {
            // TODO: empty state
            return
        }

        CharacterListHeaderEpoxyModel("Main Family").id("main_family_header").addTo(this)
        super.addModels(models.subList(0, 5))

        (models.subList(5, models.size) as List<CharacterListItemEpoxyModel>).groupBy {
            it.character.name[0].uppercaseChar()
        }.forEach { mapEntry ->
            val character = mapEntry.key.toString().uppercase(Locale.US)
            CharacterListHeaderEpoxyModel(text = character)
                .id(character)
                .addTo(this)

            super.addModels(mapEntry.value)
        }
    }
}

class CharacterListItemEpoxyModel(
    val character: CharacterResponse,
    val onCharacterClick: (Int) -> Unit
) :
    EpoxyViewBindingModel<CharacterListItemBinding>(R.layout.character_list_item) {
    override fun CharacterListItemBinding.bind() {
        characterImage.loadImage(character.image)
        characterName.text = character.name

        root.setOnClickListener {
            onCharacterClick(character.id)
        }
    }
}


class CharacterListHeaderEpoxyModel(
    private val text: String
) :
    EpoxyViewBindingModel<CharacterListHeaderTitleBinding>(R.layout.character_list_header_title) {
    override fun CharacterListHeaderTitleBinding.bind() {
        headerTitle.text = text
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}