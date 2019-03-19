package ch.tim.commands

import ch.tim.file.FileManager
import ch.tim.file.FileManager.GITLIN_FOLDER
import ch.tim.file.FileManager.HEAD_FILE
import ch.tim.file.FileManager.HEAD_FOLDER
import ch.tim.file.FileManager.OBJECT_FOLDER
import ch.tim.file.FileManager.REF_FOLDER
import com.github.ajalt.clikt.core.CliktCommand
import java.io.File

class Init : CliktCommand(help = "Creates an empty Gitlin repository") {

    override fun run() {
        if (FileManager.isGitlin()) {
            println("Already Initialized")
            return
        }

        // create gitlin folder
        File(GITLIN_FOLDER).mkdir()

        // create head
        File(GITLIN_FOLDER, HEAD_FILE).run {
            createNewFile()
            writeText("ref: refs/heads/master\n")
        }

        // create objects folder
        File(GITLIN_FOLDER, OBJECT_FOLDER).mkdir()

        // create heads folder
        File("$GITLIN_FOLDER/$REF_FOLDER/$HEAD_FOLDER/").mkdirs()

        println("Initialized empty Gitlin repository in .gitlin/")
    }
}