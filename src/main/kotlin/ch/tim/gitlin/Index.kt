package ch.tim.gitlin

import ch.tim.common.FileSystem
import ch.tim.common.Logger
import java.io.File

/**
 * Add file to index
 */
fun File.addToIndex() {
    val indexFile = FileSystem.getIndex()

    // get path from root directory
    val fileNameToAdd = getFileName(this)
    val lines = indexFile
        .readLines()
        .toMutableList()
        .addIfMissing(fileNameToAdd)
        .joinToString("\n")
    indexFile.writeText(lines)
}

fun File.removeFromIndex(recursively: Boolean) {
    val indexFile = FileSystem.getIndex()

    val fileNameToRemove = getFileName(this)
    val lines = indexFile
        .readLines()
        .toMutableList()
        .apply {
            // if it is a folder, but there is no content, remove it no shits given
            val isFolderWithContent = count { it.startsWith(fileNameToRemove) } > 1
            if (isFolderWithContent && !recursively) {
                Logger.error("Cannot delete a folder with content without the recursive flag (-r)")
            }
            removeAll {
                it.startsWith(fileNameToRemove)
            }
        }
        .joinToString("\n")
    // put string back in
    indexFile.writeText(lines)
}

private fun getFileName(file: File): String {
    val basePath = FileSystem.getRootDirectory(file.canonicalPath)
    return file.canonicalPath.removePrefix(basePath.canonicalPath).removePrefix("/")
}

private fun MutableList<String>.addIfMissing(lineToAdd: String): MutableList<String> {
    if (!this.contains(lineToAdd)) {
        this.add(lineToAdd)
    }
    return this
}