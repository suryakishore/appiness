package com.example.appiness

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appiness.Constants.PAGE
import com.example.appiness.databinding.ActivityMainBinding
import com.example.appiness.searchreponse.SearchPhotosData
import com.example.appiness.util.MyScrollListener

class MainActivity : AppCompatActivity(), TextWatcher {

    lateinit var mBinding: ActivityMainBinding
    lateinit var searchViewModel: SearchViewModel
    private var mSearchData = ArrayList<SearchPhotosData>()
    lateinit var adapter: SearchAdapter
    private var mPageCount: Int = 0
    private var mIndex: Int = 0
    lateinit var mInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
        subscribeSearchData()
    }

    /**
     * initialize data related to user interface  and view model and initially
     * i am calling with an api with the search key wiki.
     */
    private fun initializeData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        mBinding.viewModel = searchViewModel
        mBinding.etSearch.addTextChangedListener(this)
        mSearchData.clear()
        adapter = SearchAdapter(mSearchData)
        val layoutManager = LinearLayoutManager(this)
        mBinding.rvFlickr.adapter = adapter
        mBinding.rvFlickr.layoutManager = layoutManager
        searchViewModel.initializeRetrofit(this)
        mBinding.etSearch.setText(resources.getString(R.string.flick))
        searchViewModel.getSearchResults(PAGE, resources.getString(R.string.flick))
        mBinding.rvFlickr.addOnScrollListener(object : MyScrollListener(layoutManager) {
            override fun loadMoreItems() {
                if (mSearchData.size < mPageCount) {
                    ++mIndex
                    Log.d("exe", "mIndex  " + mIndex)
                    searchViewModel.getSearchResults(
                        mIndex, mInput
                    )
                }
            }

            override fun isLastPage(): Boolean {
                return searchViewModel.progressVisible.get() as Boolean
            }

            override fun isLoading(): Boolean {
                return searchViewModel.progressVisible.get() as Boolean
            }
        })
    }

    /**
     * subscribe to search data which is coming from server
     */
    private fun subscribeSearchData() {
        searchViewModel.onSearchData().observe(this, Observer {
            if (it != null) {
                mPageCount = it.first as Int
                mSearchData.addAll(it.second as ArrayList<SearchPhotosData>)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString() != null && !s.toString().isEmpty()) {
            mInput = s.toString()
            mSearchData.clear()
            searchViewModel.getSearchResults(PAGE, mInput)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}