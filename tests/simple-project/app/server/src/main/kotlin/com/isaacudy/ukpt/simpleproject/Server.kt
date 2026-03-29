package com.isaacudy.ukpt.simpleproject

import feature.simpleproject.domain.SimpleProjectInfo
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = {
            routing {
                get("/") {
                    call.respondText(SimpleProjectInfo.createRandom().toString())
                }
            }
        },
    ).start(wait = true)
}