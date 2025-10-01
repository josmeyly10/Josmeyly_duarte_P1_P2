package com.example.josmeyly_duarte_p1_p2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

class HuacalesEntity {
    @Entity(tableName = "huacales")
    data class huacalesEntity(
        @PrimaryKey
        val IdEntrada: Int? = null,
        val NombreCliente: String = "",
        val Fecha: Int = 0,
        val Cantidad: Int = 0,
        val Precio: Int = 0,

        )

}