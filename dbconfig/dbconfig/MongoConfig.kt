package com.tdl.motorinsurance.dbconfig


import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.connection.ConnectionPoolSettings
import com.tdl.motorinsurance.dto.RtoDTO
import com.tdl.motorinsurance.model.Nfbc
import com.tdl.motorinsurance.model.Vehicle
import com.tdl.motorinsurance.utils.Constants.IDLETIME
import com.tdl.motorinsurance.utils.Constants.MAXSIZE
import com.tdl.motorinsurance.utils.Constants.MINSIZE
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class MongoConfig(databaseName: String, databaseUrl: String) {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private var client: CoroutineClient
    private var database: CoroutineDatabase

    init {
        log.info("Loading Mongo Config")
        client = KMongo.createClient(
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(databaseUrl))
                .applyToConnectionPoolSettings {
                    ConnectionPoolSettings.builder().maxConnectionIdleTime(IDLETIME, TimeUnit.MILLISECONDS)
                        .minSize(MINSIZE).maxSize(MAXSIZE)
                }
                .applicationName("MotorInsurance")
                .build()).coroutine
        database = client.getDatabase(databaseName)
    }

    fun getDatabase(): CoroutineDatabase {
        return database
    }
}

object MMVCollection {
    var mmvCollection: List<Vehicl  e>
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    init {
        val mmvMaster = MongoConfigFactory.getDatabase().getCollection<Vehicle>("mmvmaster")
        mmvCollection = runBlocking {
            mmvMaster.find().toList()
        }
        log.info("MMV Two Wheeler Collection initialized : Size ${mmvCollection.size} ")
    }
}
object MMVFourWheelerCollection {
    var fourWheelCollection: List<Vehicle>
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    init {
        val fourWheelerMaster = MongoConfigFactory.getDatabase().getCollection<Vehicle>("fourWheelmaster")
        fourWheelCollection = runBlocking {
            fourWheelerMaster.find().toList()
        }
        log.info("MMV Four Wheeler Collection initialized : Size ${fourWheelCollection.size}")
    }
}

object RTOCollection {
    var rtoCollection: List<RtoDTO>
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    init {
        val rtoDb = MongoConfigFactory.getDatabase().getCollection<RtoDTO>("rtomaster")
        rtoCollection = runBlocking {
            rtoDb.find().toList()
        }
        log.info("RTO Collection initialized : Size ${rtoCollection.size}")
    }
}

object NBFCCollection {
    var nfbcCollection: List<Nfbc>
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    init {
        val nbfcMaster = MongoConfigFactory.getDatabase().getCollection<Nfbc>("nbfcmaster")

        nfbcCollection = runBlocking {
            nbfcMaster.find().toList()
        }
        log.info("NBFC Collection initialized : Size ${nfbcCollection.size}")
    }
}
