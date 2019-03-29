package ch.tim.common

import java.io.File

object FileSystem {

    const val GITLIN_FOLDER = ".gitlin"
    const val OBJECT_FOLDER = "object"
    const val INDEX = "index"
    const val HEAD_FILE = "head"
    const val HEAD_FOLDER = "heads"
    const val REF_FOLDER = "refs"

    /**
     * Returns the Path to the .gitlin folder, or throws an exception if it is not a .gitlin project
     */
    @Throws(IllegalStateException::class)
    fun pathToGitlin(currentPath: String): String {

        val gitlinFolder = File(currentPath.removeSuffix("."), GITLIN_FOLDER)
        Logger.log(gitlinFolder.canonicalPath)

        return if (gitlinFolder.exists()) {
            gitlinFolder.canonicalPath
        } else {
            val currentPathDirectory = File(currentPath).parent
            if (currentPathDirectory != "/") {
                pathToGitlin(currentPathDirectory)
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
            pathToGitlin("./")
            true
        } catch (e: IllegalStateException) {
            false
        }
    }

    /**
     * Gets the Root directory of the Gitlin Repository
     */
    fun getRootDirectory(currentPath: String): File {
        return File(pathToGitlin(currentPath)).parentFile
    }

    fun getIndex(): File {
        val pathToGitlinFolder = pathToGitlin("./")
        val index = File(pathToGitlinFolder, INDEX)
        if (!index.exists()) {
            index.createNewFile()
        }
        return index
    }

    /**
     * Checks if the passed file [fileToCheck] is in the repo.
     */
    fun isFileInRepo(fileToCheck: File): Boolean {
        return true
    }
}