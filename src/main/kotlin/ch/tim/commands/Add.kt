package ch.tim.commands

import ch.tim.common.FileSystem
import ch.tim.common.Logger
import ch.tim.gitlin.Ignore
import ch.tim.gitlin.addToIndex
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.types.file
import java.io.File

class Add : CliktCommand(help = "Adds a file or folder to the index") {

    private val filesToAdd by argument().file(exists = true).multiple()

    override fun run() {
        // make sure we are in a repo
        if (!FileSystem.isGitlin()) {
            Logger.info("not a gitlin repository")
            return
        }

        addFiles(filesToAdd)
    }

    private fun addFiles(filesToAdd: List<File>) {
        if (filesToAdd.isEmpty()) {
            Logger.info("Nothing specified, nothing added.")
            return
        }

        filesToAdd.forEach { file ->
            // check if file (or folder) is in .ignore file
            if (file.isDirectory) {
                // either add
                if (Ignore.canFileBeAdded(file.canonicalPath)) {
                    val files = file.walkTopDown().onEnter {
                        // ignore folders that cannot be added
                        Ignore.canFileBeAdded(file.canonicalPath)
                    }
                    files.forEach {
                        it.addToIndex()
                    }
                }
            } else if (file.isFile) {
                if (Ignore.canFileBeAdded(file.canonicalPath)) {
                    file.addToIndex()
                }
            }
        }
        Logger.info("File/s added")
    }
}