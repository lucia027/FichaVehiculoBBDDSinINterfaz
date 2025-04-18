package org.example.repository

interface CrudRepository<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(item: T): T
    fun delete(id: ID): T?
}