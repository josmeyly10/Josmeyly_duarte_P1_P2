package com.example.josmeyly_duarte_p1_p2.domain.usecase

import java.text.SimpleDateFormat
import java.util.Locale

data class HuacalesValidations(
    val isValid: Boolean,
    val error: String? = null
)

fun validateNombreCliente(value: String): HuacalesValidations {
    val v = value.trim()
    if (v.isEmpty()) return HuacalesValidations(false, "El nombre del cliente es obligatorio")
    if (v.length < 3) return HuacalesValidations(false, "El nombre del cliente debe tener al menos 3 caracteres")
    return HuacalesValidations(true)
}

fun validateCantidad(value: String): HuacalesValidations {
    val v = value.trim()
    if (v.isEmpty()) return HuacalesValidations(false, "La cantidad es obligatoria")
    val number = v.toIntOrNull() ?: return HuacalesValidations(false, "Ingrese un número entero válido")
    if (number < 0) return HuacalesValidations(false, "La cantidad debe ser mayor o igual a cero")
    return HuacalesValidations(true)
}

fun validatePrecio(value: String): HuacalesValidations {
    val v = value.trim()
    if (v.isEmpty()) return HuacalesValidations(false, "El precio es obligatorio")

    val normalized = v.replace(',', '.')
    val number = normalized.toDoubleOrNull() ?: return HuacalesValidations(false, "Ingrese un precio válido (ej: 12.50)")
    if (number < 0.0) return HuacalesValidations(false, "El precio debe ser mayor o igual a 0")
    return HuacalesValidations(true)
}

fun validateFecha(value: String): HuacalesValidations {
    val v = value.trim()
    if (v.isEmpty()) return HuacalesValidations(false, "La fecha es obligatoria")
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    sdf.isLenient = false
    return try {
        sdf.parse(v)
        HuacalesValidations(true)
    } catch (e: Exception) {
        HuacalesValidations(false, "Formato de fecha inválido (dd/MM/yyyy)")
    }
}


fun validateAll(
    nombreCliente: String,
    cantidad: String,
    fecha: String,
    precio: String
): Map<String, HuacalesValidations> {
    return mapOf(
        "nombreCliente" to validateNombreCliente(nombreCliente),
        "cantidad" to validateCantidad(cantidad),
        "fecha" to validateFecha(fecha),
        "precio" to validatePrecio(precio)
    )
}