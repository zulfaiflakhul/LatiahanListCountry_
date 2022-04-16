package binar.android.latihanlistcountry.network

import binar.android.latihanlistcountry.model.GetCountryResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("all")
    fun getCountry(): Call<ArrayList<GetCountryResponseItem>>
}