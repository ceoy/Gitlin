package ch.tim.common

import kotlin.system.exitProcess

object Logger {
    var enabled: Boolean = false
    var infoEnabled: Boolean = true

    fun log(log: String) {
        if (!enabled) return

        println(log)
    }

    fun info(info: String) {
        if (!infoEnabled) return

        println(info)
    }

    fun error(error: String) {
        println(error)
        exitProcess(0)
    }
}