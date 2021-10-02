package online.zhenhong.rickandmorty

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import online.zhenhong.rickandmorty.databinding.ActivityMainBinding
import online.zhenhong.rickandmorty.network.CharacterResponse
import online.zhenhong.rickandmorty.network.RickAndMortyApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RickAndMortyApiService.service.getCharacterById(4)
            .enqueue(object : Callback<CharacterResponse> {
                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    Log.d("CHARACTER", response.toString())
                    if (!response.isSuccessful) {
                        Toast.makeText(
                            this@MainActivity,
                            "Unsuccessful network call!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val character = response.body()!!
                        binding.nameValue.text = character.name
                        binding.statusValue.text = character.status
                        setStatusColor(binding.statusValue, character.status)

                        binding.genderValue.text = character.gender
                        binding.speciesValue.text = character.species
                        binding.originValue.text = character.origin.name
                        binding.locationValue.text = character.location.name

                        binding.headerImage.load(character.image)
                    }
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    Log.d("CHARACTER", t.localizedMessage)
                }
            })
    }

    private fun setStatusColor(statusView: TextView, status: String) {
        var textColor: Int = R.color.status_unknown

        if (status.equals("alive", true)) {
            textColor = R.color.status_alive
        } else if (status.equals("dead", true)) {
            textColor = R.color.status_dead
        }

        // https://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code
        statusView.setTextColor(ContextCompat.getColor(applicationContext, textColor))


    }
}