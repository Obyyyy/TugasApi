package com.example.tugasapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tugasapi.network.ApiClient
import com.example.tugasapi.network.DataApi
import com.example.tugasapi.network.DataApiResponse
import com.example.tugasapi.network.ScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiViewModel( private val repository: Repository
            = Repository(ApiClient.apiService) ) : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _data = MutableLiveData< ScreenState<List<DataApi>?> >()

    // The external immutable LiveData for the request status
    val data: LiveData< ScreenState<List<DataApi>?> > get() = _data

    /**
     * Call getDataApi() on init so we can display status immediately.
     */
    init {
        getDataApi()
    }

    /**
     * Gets Data information from the API Retrofit service and updates the
     * [DataApi] [List] [LiveData].
     */
    private fun getDataApi(){
        val client = repository.getDatas("1")
        _data.postValue(ScreenState.Loading(null))
        client.enqueue(object : Callback<DataApiResponse>{
            override fun onResponse(
                call: Call<DataApiResponse>,
                response: Response<DataApiResponse>
            ) {
                if(response.isSuccessful){
                    _data.postValue(ScreenState.Success(response.body()?.result))
                } else {
                    _data.postValue(ScreenState.Error(response.code().toString(), null))
                }
            }

            override fun onFailure(call: Call<DataApiResponse>, t: Throwable) {
                //Log.d("Failure", t.message.toString())
                _data.postValue(ScreenState.Error(t.message.toString(), null))
            }
        })
    }

}