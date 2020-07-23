package com.example.appiness.searchreponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResponse {


    @Expose
    @SerializedName("photos")
    private val photos: SearchPhotos? = null
    public fun getPhotos(): SearchPhotos? {
        return photos
    }


}