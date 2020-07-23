package com.example.appiness.searchreponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchPhotos {
    @Expose
    @SerializedName("total")
    private val total: String? = null
    public fun getTotalItems(): String? {
        return total
    }
    @Expose
    @SerializedName("photo")
    private val photo: ArrayList<SearchPhotosData>? = null

    public fun getPhotosData(): ArrayList<SearchPhotosData>? {
        return photo;
    }
}