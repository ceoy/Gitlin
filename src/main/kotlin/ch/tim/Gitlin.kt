package ch.tim

import ch.tim.commands.Add
import ch.tim.commands.Init
import ch.tim.commands.Push
import ch.tim.commands.Rm
import ch.tim.common.Logger
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption

class Gitlin : CliktCommand(help = "Gitlin is awesome") {

    private val debug by option("-d", "--debug", help = "should debugging statements be shown").flag(default = false)
    private val infoDisabled by option("-s", "--silence", help = "should general info logs be hidden").flag(default = false)

    override fun run() {
        // delegate proper values to the Logger
        Logger.enabled = debug
        Logger.infoEnabled = !infoDisabled
    }

    init {
        versionOption("0.1")
    }
}

fun main(args: Array<String>) = Gitlin()
    .subcommands(Init(), Add(), Push(), Rm())
    .main(args)