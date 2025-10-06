package com.example.josmeyly_duarte_p1_p2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.josmeyly_duarte_p1_p2.data.local.entities.HuacalesEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface HuacalesDao {

        @Query("SELECT * FROM huacales ORDER BY IdEntrada DESC")
        fun observeAll(): Flow<List<HuacalesEntity>>

        @Query(value= "SELECT * FROM huacales WHERE IdEntrada = :id")
        suspend fun getById(id: Int?): HuacalesEntity?

        @Upsert
        suspend fun upsert(entity: HuacalesEntity): Long

        @Delete
        suspend fun delete(entity: HuacalesEntity)

        @Query(value= "DELETE FROM huacales WHERE IdEntrada = :id")
        suspend fun deleteById(id: Int)

        @Query("SELECT EXISTS(SELECT 1 FROM huacales WHERE LOWER(TRIM(nombrecliente)) = LOWER(TRIM(:nombrecliente)) AND (:excludeId IS NULL OR IdEntrada != :excludeId))")
        suspend fun existeNombreCliente(nombrecliente: String, excludeId: Int?): Boolean
    }


