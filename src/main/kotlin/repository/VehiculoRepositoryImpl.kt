package org.example.repository

import org.example.database.DataBaseManager
import org.example.database.DataBaseService
import org.example.models.Vehiculo

class VehiucloRepositoryImpl(private val database: DataBaseManager = DataBaseService()) : VehiculoRepository {

    override fun findAll(): List<Vehiculo> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Vehiculo? {
        TODO("Not yet implemented")
    }

    override fun save(item: Vehiculo): Vehiculo {
        TODO("Not yet implemented")
    }

    override fun delete(id: String): Vehiculo? {
        TODO("Not yet implemented")
    }
}