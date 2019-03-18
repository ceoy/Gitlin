package ch.tim

import com.github.ajalt.clikt.core.CliktCommand

class Init : CliktCommand(help = "Creates an empty Gitlin repository") {
    override fun run() {
        println("Initialized empty Gitlin repository in .gitlin/")
    }
}