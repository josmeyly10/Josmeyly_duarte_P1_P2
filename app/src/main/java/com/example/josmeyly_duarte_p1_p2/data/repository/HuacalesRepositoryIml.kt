package com.example.josmeyly_duarte_p1_p2.data.repository

import android.util.Log
import com.example.josmeyly_duarte_p1_p2.data.local.dao.HuacalesDao
import com.example.josmeyly_duarte_p1_p2.data.mapper.toEntity
import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales
import com.example.josmeyly_duarte_p1_p2.domain.repository.HuacalesRepository
import com.example.josmeyly_duarte_p1_p2.data.mapper.toDomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

    class HuacalesRepositoryImpl @Inject constructor(
        private val dao: HuacalesDao
    ) : HuacalesRepository {

        override fun observeHuacales(): Flow<List<Huacales>> =
            dao.observeAll().map { list ->
                Log.d("HuacalesRepository", "Room devolvió ${list.size} registros")
                list.map { it.toDomain() }
            }

        override suspend fun getHuacales(id: Int?): Huacales? = dao.getById(id)?.toDomain()

        override suspend fun upsert(huacales: Huacales): Int {
            val id = dao.upsert(huacales.toEntity())
            return id.toInt()
        }

        override suspend fun delete(id: Int): Unit {
            dao.deleteById(id)
        }

        override suspend fun deleteById(id: Int) {
            dao.deleteById(id)
        }

        override suspend fun existeNombreCliente(nombrecliente: String, excludeId: Int?): Boolean {
            return dao.existeNombreCliente(nombrecliente, excludeId)
        }

    }