package org.example.database

import org.apache.ibatis.jdbc.ScriptRunner
import org.example.config.Config
import org.lighthousegames.logging.logging
import java.io.PrintWriter
import java.io.Reader
import java.sql.Connection
import java.sql.DriverManager

class DataBaseService :DataBaseManager {

    private val logger = logging()
    private var connection: Connection? = null

    init{
        initConexion()
        if (Config.databaseInitTables){
            initTablas()
        }
        if (Config.databaseInitData){
            initData()
        }
    }
    fun initConexion(){
        if (connection != null || connection!!.isClosed){
            connection = DriverManager.getConnection(Config.databaseUrl)
        }
    }

    fun initTablas(){
        try {
            val tablas = ClassLoader.getSystemResourceAsStream("tables.sql")?.bufferedReader()!!
            scriptRunner(tablas, true)
        }catch (e: Exception){
            logger.error { "Error al cargar las tablas" }
        }
    }

    fun initData(){
        try {
            val data = ClassLoader.getSystemResourceAsStream("data.sql")?.bufferedReader()!!
            scriptRunner(data, true)
        }catch (e: Exception){
            logger.error { "Error al cargar los datos" }
        }
    }

    override fun select(query: String, vararg params: Any): List<Map<String, Any>> {
        TODO("Not yet implemented")
    }

    override fun insert(query: String, vararg params: Any): Int {
        TODO("Not yet implemented")
    }

    override fun delete(query: String, vararg params: Any): Int {
        TODO("Not yet implemented")
    }


    private fun scriptRunner(reader: Reader, logWriter: Boolean = false) {
        val sr = ScriptRunner(connection)
        sr.setLogWriter(if (logWriter) PrintWriter(System.out) else null)
        sr.runScript(reader)
    }
}