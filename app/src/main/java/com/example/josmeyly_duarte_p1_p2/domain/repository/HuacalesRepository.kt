package com.example.josmeyly_duarte_p1_p2.domain.repository

import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales
import kotlinx.coroutines.flow.Flow

interface HuacalesRepository {

    fun observeHuacales(): Flow<List<Huacales>>

    suspend fun getHuacales(id: Int?): Huacales?

    suspend fun upsert(huacales: Huacales): Int

    suspend fun delete(id: Int)

    suspend fun existeNombreCliente(nombrecliente: String, excludeId: Int? = null): Boolean

    suspend fun deleteById(id: Int)
}