package com.riad.pexelsdemoapp.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.riad.pexelsdemoapp.R
import com.riad.pexelsdemoapp.data.repository.NetworkState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG: String = MainActivity::class.java.getName()

    private lateinit var photoListAdapter: PhotoListAdapter

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()


    }

    private fun initView(){

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                viewModel.getSearchPhotos(query)
                return false
            }

        })

        photoListAdapter = PhotoListAdapter(this)
        rv_photo_list.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            // specify an viewAdapter (see also next example)
            adapter = photoListAdapter
        }

        viewModel.searchResponse.observe(this, Observer {
            Log.d(TAG, "searchResponse: " + Gson().toJson(it))
            photoListAdapter.setDataList(it.photos)
            txt_response_message.visibility = if ( it.total_results == 0 ) View.VISIBLE else View.GONE
        })

        viewModel.networkState.observe(this, Observer {
            Log.d(TAG, it.toString())
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if ( it == NetworkState.ERROR || it == NetworkState.NO_CONNECTION) View.VISIBLE else View.GONE

            if (it == NetworkState.NO_CONNECTION){
                txt_error.text = getString(R.string.error_no_internet)
            } else if(it == NetworkState.ERROR){
                txt_error.text = getString(R.string.error_server)
            }

        })

    }

    override fun onResume() {
        super.onResume()
        search_view.clearFocus()
    }

}