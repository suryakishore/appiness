package com.example.appiness

import android.content.Context
import androidx.core.util.Pair
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appiness.Constants.API_KEY
import com.example.appiness.Constants.FORMAT
import com.example.appiness.Constants.METHOD
import com.example.appiness.Constants.NO_JSON_CALL
import com.example.appiness.Constants.PER_PAGE
import com.example.appiness.Constants.SUCCESS
import com.example.appiness.searchreponse.SearchPhotosData
import com.example.appiness.searchreponse.SearchResponse
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

/**
 * view model class for the main activity.
 */
class SearchViewModel() : ViewModel() {
    val progressVisible = ObservableField<Boolean>(false)
    private var mSearchData = MutableLiveData<Pair<Int, ArrayList<SearchPhotosData>>>()
    private var networkService: NetworkService? = null
    public fun initializeRetrofit(context: Context) {

        networkService = NetworkManager.initializeBaseUrlRetrofit(context)


    }

    /**
     * This method used to get the search Data.
     */
    public fun getSearchResults(pageCount: Int, search: String) {
        progressVisible.set(true)
        networkService.also { it ->
            it!!.getSearchResults(
                METHOD,
                API_KEY,
                search,
                FORMAT,
                NO_JSON_CALL,
                PER_PAGE,
                pageCount
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (it != null) {
                        progressVisible.set(false)
                        try {
                            val jsonObject: JSONObject
                            val code = it.code()
                            if (code == SUCCESS) {
                                val response: String = it.body()!!.string()
                                jsonObject = JSONObject(response)
                                val gson = Gson()
                                val searchResponse =
                                    gson.fromJson(jsonObject.toString(), SearchResponse::class.java)
                                if (searchResponse != null && searchResponse.getPhotos()!!
                                        .getTotalItems() != null
                                ) {
                                    mSearchData.postValue(
                                        Pair.create(
                                            searchResponse.getPhotos()!!.getTotalItems()!!.toInt(),
                                            searchResponse.getPhotos()!!.getPhotosData()
                                        )
                                    )
                                }
                            } else {
                                jsonObject = JSONObject(it.errorBody()!!.string())
                                mSearchData.postValue(null)
                            }
                        } catch (e: JSONException) {
                            mSearchData.postValue(null)
                        } catch (e: IOException) {
                            mSearchData.postValue(null)
                        }
                    }
                }
        }
    }


    /**
     * notify activity search data comes
     */
    fun onSearchData(): MutableLiveData<Pair<Int, ArrayList<SearchPhotosData>>> {
        return mSearchData
    }


}