package repository

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.example.dao.VehiculoDao
import org.example.dao.VehiculoEntity
import org.example.mapper.toModel
import org.example.repository.VehiculoRepositoryImpl
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
class VehiculoRepositoryImplTest {

    @MockK
    private lateinit var dao: VehiculoDao

    @InjectMockKs
    private lateinit var repositoryImpl: VehiculoRepositoryImpl

    @Test
    @DisplayName("FindAll")
    fun findAll() {
        val vehiculoEntity = VehiculoEntity(
            matricula = "1234MVP",
            marca = "Toyota",
            modelo = "Supra"
        )

        every { dao.findAll() } returns listOf(vehiculoEntity)

        val vehiculos = repositoryImpl.findAll()

        assertAll(
            { assertNotNull(vehiculos.size == 1, "El vehiculo no es nulo") },
            { assertEquals(1, vehiculos.size, "Tiene que devolver 1") },
            { assertEquals("1234MVP", vehiculos[0].matricula, "Matricvula correcta") },
            { assertEquals("Toyota", vehiculos[0].marca, "Marca correcta") },
            { assertEquals("Supra", vehiculos[0].modelo, "Modelo correcta") }
        )

        verify(atLeast = 1) { dao.findAll() }
    }

    @Test
    @DisplayName("FindById")
    fun findById() {
        val vehiculoEntity = VehiculoEntity(
            matricula = "1234MVP",
            marca = "Toyota",
            modelo = "Supra"
        )

        every { dao.findById("1234MVP") } returns vehiculoEntity

        val vehiculo = repositoryImpl.findById("1234MVP")

        assertAll(
            { assertNotNull(vehiculo, "El vehiculo no es nulo") },
            { assertEquals("1234MVP", vehiculo!!.matricula, "Matricula correcta") },
            { assertEquals("Toyota", vehiculo!!.marca, "Marca correcta") },
            { assertEquals("Supra", vehiculo!!.modelo, "Modelo correcto") }
        )

        verify(atLeast = 1) { dao.findById("1234MVP") }
    }

    @Test
    @DisplayName("Save")
    fun save() {
        val vehiculoEntity = VehiculoEntity(
            matricula = "1234MVP",
            marca = "Toyota",
            modelo = "Supra"
        )

        val vehiculoModel = vehiculoEntity.toModel().copy(matricula = "1234MVP")

        every {
            dao.save(match{
                it.marca == "Toyota" && it.modelo == "Supra"
            })
        } returns vehiculoEntity

        val result = repositoryImpl.save(vehiculoModel)

        assertAll(
            { assertNotNull(result, "El vehiculo no es nulo") },
            { assertEquals("1234MVP", result.matricula, "Matricula correcta") },
            { assertEquals("Toyota", result.marca, "Marca correcta") },
            { assertEquals("Supra", result.modelo, "Modelo correcto") },
        )

        verify(atLeast = 1) { dao.save(match{it.matricula == "1234MVP" }) }
    }

}