package mihon.domain.upcoming.interactor

import eu.kanade.tachiyomi.source.model.SManga
import kotlinx.coroutines.flow.Flow
import tachiyomi.domain.manga.model.Manga
import tachiyomi.domain.manga.repository.MangaRepository

/**
 * Interactor responsible for retrieving the upcoming mangas for "Manga Akojdad".
 *
 * @property mangaRepository Repository to fetch manga data.
 */
class GetUpcomingManga(
    private val mangaRepository: MangaRepository,
) {

    // Define the statuses of mangas that are considered "upcoming" or ongoing
    private val includedStatuses = setOf(
        SManga.ONGOING.toLong(),
        SManga.PUBLISHING_FINISHED.toLong(),
    )

    /**
     * Retrieves a flow of upcoming mangas based on the status of ongoing or recently finished mangas.
     *
     * @return A flow of a list of upcoming mangas.
     */
    suspend fun subscribe(): Flow<List<Manga>> {
        return mangaRepository.getUpcomingManga(includedStatuses)
    }
}