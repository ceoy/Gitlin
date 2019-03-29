package ch.tim.commands

import ch.tim.common.FileSystem
import ch.tim.common.Logger
import ch.tim.gitlin.removeFromIndex
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import java.io.File

class Rm : CliktCommand(help = "Removes a file from the working tree and the index") {

    // files to not have to exist => if we removed it and want to removeFromIndex it from the index, it should be possible
    private val filesToRemove by argument().file().multiple()
    private val recursively by option("-r", "--recursive").flag()

    override fun run() {
        if (!FileSystem.isGitlin()) {
            Logger.info("not a gitlin repository")
            return
        }

        removeFiles(filesToRemove)
    }

    private fun removeFiles(filesToAdd: List<File>) {
        if (filesToAdd.isEmpty()) {
            Logger.info("Nothing specified, nothing removed.")
            return
        }

        filesToAdd.forEach { file ->
            file.removeFromIndex(recursively)
        }

        Logger.info("File/s removed")
    }
}