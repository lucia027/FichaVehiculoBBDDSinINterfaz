package org.example.database

import java.io.BufferedReader

interface DataBaseManager {
    fun select(query: String, vararg params: Any): List<Map<String, Any>>
    fun insert(query: String, vararg params: Any): Int
    fun delete(query: String, vararg params: Any): Int
}