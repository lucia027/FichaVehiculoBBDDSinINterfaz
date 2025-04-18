package org.example.mapper

import org.example.models.Vehiculo
import org.example.dao.VehiculoEntity

fun VehiculoEntity.toModel() : Vehiculo {
    return Vehiculo(
        matricula = this.matricula,
        marca = this.marca,
        modelo = this.modelo
    )
}

fun Vehiculo.toEntity() : VehiculoEntity {
    return VehiculoEntity(
        matricula = this.matricula,
        marca = this.marca,
        modelo = this.modelo
    )
}