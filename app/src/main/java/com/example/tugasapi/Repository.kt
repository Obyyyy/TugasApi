package com.example.tugasapi

import com.example.tugasapi.network.ApiService

class Repository ( private val apiService: ApiService) {
    fun getDatas(page:String) = apiService.fetchDatas(page)
}