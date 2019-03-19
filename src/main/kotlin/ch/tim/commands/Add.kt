package ch.tim.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.types.file

class Add : CliktCommand(help = "Adds a file to the index") {

    private val filesToAdd by argument().file(exists = true).multiple()

    override fun run() {
        println(if (filesToAdd.isEmpty()) "Empty files" else "file added")
    }
}