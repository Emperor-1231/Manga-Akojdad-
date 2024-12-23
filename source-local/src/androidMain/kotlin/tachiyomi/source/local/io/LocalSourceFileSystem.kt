package tachiyomi.source.local.io

import android.content.Context
import com.hippo.unifile.UniFile
import tachiyomi.core.common.util.system.logcat
import logcat.LogPriority

class LocalSourceFileSystem(
    private val context: Context
) {
    private val baseDirectory: UniFile by lazy {
        val directory = UniFile.fromUri(context, "file://path/to/local/directory")
        directory.takeIf { it.exists() } ?: error("Directory does not exist")
    }

    fun getBaseDirectory(): UniFile {
        return baseDirectory
    }

    fun getMangaDirectory(mangaUrl: String): UniFile? {
        return baseDirectory.findFile(mangaUrl)
    }

    fun getFilesInBaseDirectory(): List<UniFile> {
        return baseDirectory.listFiles().orEmpty()
    }

    fun getFilesInMangaDirectory(mangaUrl: String): List<UniFile> {
        return getMangaDirectory(mangaUrl)?.listFiles().orEmpty()
    }

    fun UniFile.createFile(fileName: String): UniFile? {
        return this.createFile(fileName)
    }
}