package com.pb.funora.home.viewmodel

import com.pb.funora.home.model.HomeData

data class HomeState(
    val data: HomeData? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
