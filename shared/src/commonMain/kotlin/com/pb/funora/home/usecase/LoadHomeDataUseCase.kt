package com.pb.funora.home.usecase

import com.pb.funora.home.model.HomeData
import com.pb.funora.home.repository.HomeRepository

class LoadHomeDataUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(): HomeData = repository.getHomeData()
}
