package ch.tim.file

import java.io.File

object FileManager {

    const val GITLIN_FOLDER = ".gitlin"
    const val OBJECT_FOLDER = "object"
    const val HEAD_FILE = "head"
    const val HEAD_FOLDER = "heads"
    const val REF_FOLDER = "refs"

    /**
     * Returns the Path to the .gitlin folder, or throws an exception if it is not a .gitlin project
     */
    @Throws(IllegalStateException::class)
    fun absolutePathToGitlin(currentPath: String): String {
        val gitlinFolderPath = currentPath + GITLIN_FOLDER
        val gitlinFolder = File(gitlinFolderPath)

        return if (gitlinFolder.exists()) {
            gitlinFolderPath
        } else {
            val currentPathDirectory = File(currentPath).absolutePath.replaceAfterLast('/', "")

            if (currentPathDirectory != "/") {
                absolutePathToGitlin(currentPathDirectory)
            } else {
                throw IllegalStateException("gitlin not found")
            }
        }
    }

    /**
     * Checks if the current directory is already a gitlin repository
     */
    fun isGitlin(): Boolean {
        return try {
            absolutePathToGitlin("./")
            true
        } catch (e: IllegalStateException) {
            false
        }
    }
}