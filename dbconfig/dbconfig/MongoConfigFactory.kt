package com.tdl.motorinsurance.dbconfig

import org.litote.kmongo.coroutine.CoroutineDatabase

object MongoConfigFactory {

    private val mongoClient = MongoConfig(Configuration.env.dbName, Configuration.env.dbURL)
    fun getDatabase(): CoroutineDatabase {
        return mongoClient.getDatabase()
    }
}
