package com.example.tugasapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tugasapi.network.DataApi
import com.example.tugasapi.network.ScreenState
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val viewModel: ApiViewModel by lazy {
        ViewModelProvider(this).get(ApiViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.data.observe(this, { state->
            processDataResponse(state)
        })
    }

    private fun processDataResponse(state: ScreenState<List<DataApi>?>){

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        when(state){
            is ScreenState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                progressBar.visibility = View.GONE
                if(state.data != null){
                    val adapter = MainAdapter(state.data)
                    val recyclerView = findViewById<RecyclerView>(R.id.rv)

                    recyclerView?.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView?.adapter = adapter
                }

            }
            is ScreenState.Error -> {
                progressBar.visibility = View.GONE

                val view = progressBar.rootView
                Snackbar.make(view, state.message!!, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}