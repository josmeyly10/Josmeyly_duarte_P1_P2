package com.example.josmeyly_duarte_p1_p2.data.mapper

import com.example.josmeyly_duarte_p1_p2.data.local.entities.HuacalesEntity
import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales

fun Huacales.toDomain(): Huacales = Huacales(
    IdEntrada = IdEntrada ?: 0,
    NombreCliente = NombreCliente,
    Fecha = Fecha,
    Cantidad = Cantidad,
    Precio = Precio,

)

fun Huacales.toEntity(): HuacalesEntity = HuacalesEntity(
    IdEntrada = IdEntrada,
    NombreCliente = NombreCliente,
    Fecha = Fecha,
    Cantidad = Cantidad,
    Precio = Precio,
)