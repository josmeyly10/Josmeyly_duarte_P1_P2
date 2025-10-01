package com.example.josmeyly_duarte_p1_p2.domain.usecase

import com.example.josmeyly_duarte_p1_p2.domain.repository.HuacalesRepository
import javax.inject.Inject

class DeleteHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
){
    suspend operator fun invoke(id: Int) = repository.delete(id)
}