package tachiyomi.source.local.image

import android.content.Context
import tachiyomi.domain.manga.model.SManga
import tachiyomi.source.local.io.LocalSourceFileSystem
import tachiyomi.core.common.storage.extension
import tachiyomi.core.common.util.system.ImageUtil
import tachiyomi.core.common.util.system.logcat
import logcat.LogPriority
import com.hippo.unifile.UniFile
import tachiyomi.source.local.metadata.fillMetadata
import java.io.InputStream

class LocalCoverManager(
    private val context: Context,
    private val fileSystem: LocalSourceFileSystem
) {
    fun find(mangaName: String): UniFile? {
        val mangaDir = fileSystem.getMangaDirectory(mangaName) ?: return null
        return findCoverInDirectory(mangaDir)
    }

    private fun findCoverInDirectory(mangaDir: UniFile): UniFile? {
        val coverFile = mangaDir.listFiles().find {
            ImageUtil.isImage(it.name) { it.openInputStream() }
        }
        return coverFile
    }

    fun update(manga: SManga, coverInputStream: InputStream) {
        val mangaDir = fileSystem.getMangaDirectory(manga.url) ?: return
        val coverFile = mangaDir.createFile("cover.jpg")
        coverFile?.openOutputStream()?.use { outputStream ->
            coverInputStream.copyTo(outputStream)
        }
        manga.thumbnail_url = coverFile?.uri.toString()
    }
}