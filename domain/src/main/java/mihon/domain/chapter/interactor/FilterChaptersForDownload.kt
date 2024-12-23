package mihon.domain.chapter.interactor

import tachiyomi.domain.category.interactor.GetCategories
import tachiyomi.domain.chapter.interactor.GetChaptersByMangaId
import tachiyomi.domain.chapter.model.Chapter
import tachiyomi.domain.download.service.DownloadPreferences
import tachiyomi.domain.manga.model.Manga

/**
 * Interactor responsible for determining which chapters of a manga should be downloaded for "Manga Akojdad".
 *
 * @property getChaptersByMangaId Interactor for retrieving chapters by manga ID.
 * @property downloadPreferences User preferences related to chapter downloads.
 * @property getCategories Interactor for retrieving categories associated with a manga.
 */
class FilterChaptersForDownload(
    private val getChaptersByMangaId: GetChaptersByMangaId,
    private val downloadPreferences: DownloadPreferences,
    private val getCategories: GetCategories,
) {

    /**
     * Determines which chapters of a manga should be downloaded based on user preferences for "Manga Akojdad".
     *
     * @param manga The manga for which chapters may be downloaded.
     * @param newChapters The list of new chapters available for the manga.
     * @return A list of chapters that should be downloaded.
     */
    suspend fun await(manga: Manga, newChapters: List<Chapter>): List<Chapter> {
        // If no new chapters are available or the user preferences don't match, return an empty list
        if (
            newChapters.isEmpty() ||
            !downloadPreferences.downloadNewChapters().get() ||
            !manga.shouldDownloadNewChapters()
        ) {
            return emptyList()
        }

        // If the user doesn't care about downloading only unread chapters, return all new chapters
        if (!downloadPreferences.downloadNewUnreadChaptersOnly().get()) return newChapters

        // Get chapters that have been read and are recognized by chapter number
        val readChapterNumbers = getChaptersByMangaId.await(manga.id)
            .asSequence()
            .filter { it.read && it.isRecognizedNumber }
            .map { it.chapterNumber }
            .toSet()

        // Filter out already read chapters, return only new unread chapters
        return newChapters.filterNot { it.chapterNumber in readChapterNumbers }
    }

    /**
     * Determines whether new chapters should be downloaded for the manga based on user preferences
     * and the categories to which the manga belongs.
     *
     * @return `true` if chapters of the manga should be downloaded.
     */
    private suspend fun Manga.shouldDownloadNewChapters(): Boolean {
        // Check if the manga is a favorite
        if (!favorite) return false

        // Get the categories the manga belongs to, defaulting to a general category if none exist
        val categories = getCategories.await(id).map { it.id }.ifEmpty { listOf(DEFAULT_CATEGORY_ID) }

        // Retrieve the categories the user has selected for download and the excluded categories
        val includedCategories = downloadPreferences.downloadNewChapterCategories().get().map { it.toLong() }
        val excludedCategories = downloadPreferences.downloadNewChapterCategoriesExclude().get().map { it.toLong() }

        return when {
            // Default Download from all categories
            includedCategories.isEmpty() && excludedCategories.isEmpty() -> true
            // In an excluded category
            categories.any { it in excludedCategories } -> false
            // No specific categories included
            includedCategories.isEmpty() -> true
            // In an included category
            else -> categories.any { it in includedCategories }
        }
    }

    companion object {
        private const val DEFAULT_CATEGORY_ID = 0L
    }
}