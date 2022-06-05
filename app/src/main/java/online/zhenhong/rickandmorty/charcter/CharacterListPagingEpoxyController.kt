package online.zhenhong.rickandmorty.charcter

import android.util.Log
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import online.zhenhong.rickandmorty.R
import online.zhenhong.rickandmorty.databinding.CharacterListItemBinding
import online.zhenhong.rickandmorty.epoxy.EpoxyViewBindingModel
import online.zhenhong.rickandmorty.loadImage

class CharacterListPagingEpoxyController : PagedListEpoxyController<CharacterResponse>() {
    override fun buildItemModel(currentPosition: Int, item: CharacterResponse?): EpoxyModel<*> {
        return CharacterListItemEpoxyModel(item!!.image, item.name).id(item.id)
    }
}

class CharacterListItemEpoxyModel(
    private val imageUrl: String,
    val name: String
) :
    EpoxyViewBindingModel<CharacterListItemBinding>(R.layout.character_list_item) {
    override fun CharacterListItemBinding.bind() {
        Log.d("loadImage", "bind: $imageUrl")
        characterImage.loadImage(imageUrl)
        characterName.text = name
    }
}