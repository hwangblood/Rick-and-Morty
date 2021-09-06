package online.zhenhong.rickandmorty

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
                        val name = character.name
                        binding.textView.text = name
                    }
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    Log.d("CHARACTER", t.localizedMessage)
                }
            })
    }
}