package ch.tim

import ch.tim.commands.Add
import ch.tim.commands.Init
import ch.tim.commands.Push
import ch.tim.commands.Rm
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.versionOption

class Gitlin : CliktCommand(help = "Gitlin is awesome") {
    override fun run() {
        // handle options
    }

    init {
        versionOption("0.1")
    }
}

fun main(args: Array<String>) = Gitlin()
        .subcommands(Init(), Add(), Push(), Rm())
        .main(args)