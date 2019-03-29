package ch.tim.commands

import ch.tim.common.FileSystem
import ch.tim.common.FileSystem.GITLIN_FOLDER
import ch.tim.common.FileSystem.HEAD_FILE
import ch.tim.common.FileSystem.HEAD_FOLDER
import ch.tim.common.FileSystem.OBJECT_FOLDER
import ch.tim.common.FileSystem.REF_FOLDER
import ch.tim.common.Logger
import com.github.ajalt.clikt.core.CliktCommand
import java.io.File

/**
 * Initializes
 */
class Init : CliktCommand(help = "Creates an empty Gitlin repository") {

    override fun run() {
        if (FileSystem.isGitlin()) {
            Logger.info("Already Initialized")
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

        Logger.info("Initialized empty Gitlin repository in .gitlin/")
    }
}