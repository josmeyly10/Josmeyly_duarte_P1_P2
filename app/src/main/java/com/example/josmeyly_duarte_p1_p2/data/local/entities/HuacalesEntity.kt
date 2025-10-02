package com.example.josmeyly_duarte_p1_p2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


    @Entity(tableName = "huacales")
    data class HuacalesEntity(
        @PrimaryKey
        val IdEntrada: Int? = null,
        val NombreCliente: String = "",
        val Fecha: String =  "",
        val Cantidad: Int = 0,
        val Precio: Int = 0,

        )

