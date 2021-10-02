package online.zhenhong.rickandmorty

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    url?.let { load(it) { crossfade(true) } }
}

@BindingAdapter("statusColor")
fun TextView.setStatusColor(status: String?){

    var textColor: Int = R.color.status_unknown

    if (status.equals("alive", true)) {
        textColor = R.color.status_alive
    } else if (status.equals("dead", true)) {
        textColor = R.color.status_dead
    }

    // https://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code
    setTextColor(ContextCompat.getColor(context, textColor))
}