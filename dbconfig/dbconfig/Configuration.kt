package com.tdl.motorinsurance.dbconfig

import com.tdl.motorinsurance.model.ConfigParameters
import io.ktor.server.application.ApplicationEnvironment

object Configuration {

    lateinit var env: ConfigParameters

    fun initConfig(environment: ApplicationEnvironment) {
        val isTDLRiskcovry = environment.config.property("ktor.api.isTDLRiskcovry").getString().toBoolean()
        env = ConfigParameters(
            dbURL = environment.config.property("ktor.db.dbURL").getString(),
            dbName = environment.config.property("ktor.db.dbName").getString(),
            riskcovryPartnerCode = environment.config.property("ktor.api.riskcovryPartnerCode").getString(),
            riskcovryPartnerKey = if (isTDLRiskcovry)
                environment.config.property("ktor.api.riskcovryTDLPartnerKey").getString()
            else environment.config.property("ktor.api.riskcovryPartnerKey").getString(),
            riskcovryBaseURL = if (isTDLRiskcovry) environment.config.property("ktor.api.riskcovryTDLBaseURL")
                .getString()
            else environment.config.property("ktor.api.riskcovryBaseURL").getString(),
            onGridBaseURL = environment.config.property("ktor.api.onGridBaseURL").getString(),
            onGridAPIKey = environment.config.property("ktor.api.onGridAPIKey").getString(),
            onGridAPIMappingKey = environment.config.property("ktor.api.onGridAPIMappingKey").getString(),
            onGridAPIMappingKeyFourWheeler = environment.config.property("ktor.api.onGridAPIMappingKeyFourWheeler")
                .getString(),
            signzyBaseURL = environment.config.property("ktor.api.singzyBaseURL").getString(),
            paymentBaseURL = environment.config.property("ktor.api.paymentBaseURL").getString(),
            paymentClientId = environment.config.property("ktor.api.paymentClientId").getString(),
            signzyUsername = environment.config.property("ktor.api.signzyUsername").getString(),
            signzyPassword = environment.config.property("ktor.api.signzyPassword").getString(),
            searchLimit = environment.config.property("ktor.api.maximumSearchLimit").getString(),
            azureEventHubConnectionString = environment.config.property("ktor.client.azureEventHubConnectionString")
                .getString(),
            azureEventHubName = environment.config.property("ktor.client.azureEventHubName").getString(),
            merchantId = environment.config.property("ktor.api.merchantId").getString(),
            merchantName = environment.config.property("ktor.api.merchantName").getString(),
            terminalId = environment.config.property("ktor.api.terminalId").getString(),
            signzyTask = environment.config.property("ktor.api.signzyTask").getString(),
            onGridConsent = environment.config.property("ktor.api.onGridConsent").getString(),
            returnUrl = environment.config.property("ktor.api.returnUrl").getString()
        )
    }
}
