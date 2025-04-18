package org.example.config

import org.lighthousegames.logging.logging
import java.util.*

object Config {
    val logger = logging()
    var locale: String = Locale.getDefault().toString()

    var databaseUrl: String = "jdbc:h2:tcp://localhost:9092/default"
        private set
    var databaseInitTables: Boolean = false
        private set
    var databaseInitData: Boolean = false
        private set
    var storageData: String = "data"
        private set

    init {
        try {
            val properties = Properties()

            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseInitTables = properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData = properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()
            storageData = properties.getProperty("storage.data", this.storageData)
            locale = properties.getProperty("locale", this.locale)

            logger.debug { "Configuracion cargada" }
        }catch(e:Exception){
            logger.error { "Eroro cargando la configuracion" }
        }
    }
}