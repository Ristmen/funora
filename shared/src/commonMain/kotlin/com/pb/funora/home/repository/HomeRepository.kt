package com.pb.funora.home.repository

import com.pb.funora.home.data.HomeService
import com.pb.funora.home.model.HomeData

class HomeRepository(private val service: HomeService) {
    suspend fun getHomeData(): HomeData = service.fetchHomeData()
}
