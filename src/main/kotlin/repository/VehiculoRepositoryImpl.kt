package org.example.repository


import org.example.dao.VehiculoDao
import org.example.mapper.toEntity
import org.example.mapper.toModel
import org.example.models.Vehiculo
import org.jetbrains.kotlin.gradle.idea.proto.com.google.protobuf.copy
import org.lighthousegames.logging.logging

class VehiculoRepositoryImpl(val dao: VehiculoDao) : VehiculoRepository {
    val logger = logging()

    override fun findAll(): List<Vehiculo> {
        return dao.findAll().map { it.toModel() }
    }

    override fun findById(matricula: String): Vehiculo? {
        return dao.findById(matricula)?.toModel()
    }

    override fun save(item: Vehiculo): Vehiculo {
        val matricula = dao.save(item.toEntity())

        return item.copy(matricula = matricula)
    }

    override fun delete(matricula: String): Vehiculo? {
        val vehiculo = dao.findById(matricula)

        if (vehiculo != null) {
            val result = dao.delete(matricula)
            if (result == 0) {
                logger.error { "No se puede borrar el vehiculo con la matriucla: $matricula" }
            }
        }
        return vehiculo as Vehiculo?
    }
}