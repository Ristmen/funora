package com.pb.funora.home.data

import com.pb.funora.home.model.HomeData

expect class HomeService() {
    suspend fun fetchHomeData(): HomeData
}
