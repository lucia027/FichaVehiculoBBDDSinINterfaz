package org.example.repository

import org.example.models.Vehiculo

interface VehiculoRepository: CrudRepository<Vehiculo, String>{
    override fun findById(matricula: String): Vehiculo?
    override fun delete(matricula: String): Vehiculo?
}