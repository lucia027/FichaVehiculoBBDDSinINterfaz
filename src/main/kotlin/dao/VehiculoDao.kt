package org.example.dao

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jetbrains.kotlin.javax.inject.Singleton

@RegisterKotlinMapper(VehiculoEntity::class)
interface VehiculoDao {

    @SqlQuery("SELECT * FROM Vehiculo")
    fun findAll(): List<VehiculoEntity>

    @SqlQuery("SELECT * FROM Vehiculo WHERE matricula = :matricula")
    fun findById(@Bind("matricula") matricula: String): VehiculoEntity?

    @SqlUpdate("INSERT INTO Vehiculo (matricula, marca, modelo) VALUES (:matricula, :marca, :modelo)")
    fun save(@BindBean vehiculo: VehiculoEntity): VehiculoEntity

    @SqlUpdate("DELETE FROM Vehiculo WHERE matricula = :matricula")
    fun delete(entity: String): Int
}

@Singleton
fun provideVehiculoDao(jdbi: Jdbi): VehiculoDao {
    return jdbi.onDemand(VehiculoDao::class.java)
}