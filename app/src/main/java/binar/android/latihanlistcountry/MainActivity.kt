package binar.android.latihanlistcountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import binar.android.latihanlistcountry.model.GetCountryResponseItem
import binar.android.latihanlistcountry.network.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun fetchData() {
        ApiClient.instance.getCountry()
            .enqueue(object : Callback<ArrayList<GetCountryResponseItem>> {

                override fun onResponse(
                    call: Call<ArrayList<GetCountryResponseItem>>,
                    response: Response<ArrayList<GetCountryResponseItem>>
                ) {
                    if (response.isSuccessful){
                        showList(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<GetCountryResponseItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    Log.d("fail", t.message!!)
                }
            })
    }

    private fun showList(listCountry: List<GetCountryResponseItem>?) {
        rv_country.layoutManager = GridLayoutManager(this, 2)
        val adapter = CountryAdapter(listCountry!!)
        rv_country.adapter = adapter
    }
}