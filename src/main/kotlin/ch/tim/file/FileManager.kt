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
    fun pathToGitlin(currentPath: String): String {
        val gitlinFolderPath = currentPath + GITLIN_FOLDER
        val gitlinFolder = File(gitlinFolderPath)
        println(gitlinFolderPath)

        return if (gitlinFolder.exists()) {
            gitlinFolderPath
        } else {
            val newPath = "$currentPath/../"
            println(newPath)

            val parent = File(newPath)
            // println(parent)
            if (parent.exists() && parent.path != "/") {
                pathToGitlin(parent.path)
            } else {
                throw IllegalStateException("gitlin not found")
            }
        }
    }

    fun isGitlin(): Boolean {
        return try {
            pathToGitlin("./")
            true
        } catch (e: IllegalStateException) {
            false
        }
    }
}