package com.example.josmeyly_duarte_p1_p2.domain.usecase

data class HuacalesValidations(
    val isValid: Boolean,
    val error: String? = null
)

fun validateNombreCliente(value: String): HuacalesValidations {
    if (value.isBlank()) return HuacalesValidations(false, "El nombre del cliente es obligatorio")
    if (value.length < 3) return HuacalesValidations(false, "El nombre del cliente debe tener al menos 3 caracteres")
    return HuacalesValidations(true)
}

fun validateCantidad(value: String): HuacalesValidations {
    if (value.isBlank()) return HuacalesValidations(false, "La cantidad es obligatoria")
    val number = value.toIntOrNull()
        ?: return HuacalesValidations(false, "Ingrese un número válido para las cantidades o un numero entero")
    if (number < 0) return HuacalesValidations(false, "El número de cantidad debe ser mayor o igual a cero")
    return HuacalesValidations(true)
}