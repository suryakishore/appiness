package com.example.appiness.searchreponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchPhotosData {

    @Expose
    @SerializedName("id")
    val id: String? = null


    @Expose
    @SerializedName("farm")
    val farm: Int? = null

    @Expose
    @SerializedName("owner")
    val owner: String? = null


    @Expose
    @SerializedName("title")
    val title: String? = null


}