package online.zhenhong.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import online.zhenhong.rickandmorty.network.RickAndMortyApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RickAndMortyApiService.service.getCharacterById(4).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.d("CHARACTER", response.body().toString())
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.d("CHARACTER", t.localizedMessage)
            }
        })
    }
}