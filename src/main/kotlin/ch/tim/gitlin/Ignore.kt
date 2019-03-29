package ch.tim.gitlin

import ch.tim.common.FileSystem
import ch.tim.common.Logger
import java.io.File

/**
 * TODO:
 * > figure out how to properly ignore files
 * .ignore file
 */
object Ignore {

    private const val IGNORE_FILE = ".ignore"

    private fun loadContent(filePath: String) {

        val rootDirectoryPath = try {
            FileSystem.getRootDirectory(filePath).canonicalPath
        } catch (e: IllegalStateException) {
            // not a gitlin project
            return
        }

        // walk from root to current directory, finding every .ignore file
        // every .ignore file that is not in the root directory should only affect files that are in the path
        val content = mutableListOf<String>()
        var currentFolder = File(filePath).parent
        if (rootDirectoryPath == currentFolder) {
            content.addAll(readIgnoreFileInPath(currentFolder))
        } else {
            do {
                // load .ignore
                content.addAll(readIgnoreFileInPath(currentFolder))
                currentFolder = File(currentFolder).parent
            } while (rootDirectoryPath != currentFolder)
        }
    }

    private fun readIgnoreFileInPath(currentFolder: String): List<String> {
        val ignoreFile = File(currentFolder, IGNORE_FILE)
        if (ignoreFile.exists() && ignoreFile.isFile) {
            return ignoreFile.readLines().mapNotNull {
                if (it.startsWith("#") || it.isBlank()) {
                    null // skip comments and empty lines, nobody cares
                } else {
                    // todo: parse this to some sort of a regex string?
                    "${currentFolder.replace("/", "\\/")}\\/${
                    it
                        .replace("/", "\\/")
                        .replace(".", "\\.")
                        .replace("*", ".*")}"
                }
            }
        }
        return listOf()
    }

    fun canFileBeAdded(filePath: String): Boolean {
        // loadContent(filePath)
        // todo: return true, this is still wip
        Logger.log("File can be added.")
        return true
    }
}