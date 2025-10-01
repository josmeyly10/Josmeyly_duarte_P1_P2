package com.example.josmeyly_duarte_p1_p2.domain.usecase

import com.example.josmeyly_duarte_p1_p2.domain.repository.HuacalesRepository
import javax.inject.Inject

class ExisteHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
){
    suspend operator fun invoke(nombrecliente: String, excludeId: Int? = null): Boolean {
        return repository.existeNombreCliente(nombrecliente, excludeId)
    }
}