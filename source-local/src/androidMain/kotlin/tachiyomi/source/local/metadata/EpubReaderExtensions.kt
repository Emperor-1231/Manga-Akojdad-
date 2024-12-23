package tachiyomi.source.local.metadata

import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.SManga
import mihon.core.archive.EpubReader
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Fills manga and chapter metadata using this epub file's metadata.
 * This function extracts metadata from the EPUB file and assigns it to the manga and chapter objects.
 */
fun EpubReader.fillMetadata(manga: SManga, chapter: SChapter) {
    // Get the reference to the package file (usually metadata)
    val ref = getPackageHref()

    // Parse the EPUB file's document to extract metadata
    val doc = getPackageDocument(ref)

    // Extract relevant metadata fields (title, publisher, creator, description, date)
    val title = doc.getElementsByTag("dc:title").first()
    val publisher = doc.getElementsByTag("dc:publisher").first()
    val creator = doc.getElementsByTag("dc:creator").first()
    val description = doc.getElementsByTag("dc:description").first()
    var date = doc.getElementsByTag("dc:date").first()

    // Fallback to another date element if the primary date is not found
    if (date == null) {
        date = doc.select("meta[property=dcterms:modified]").first()
    }

    // Assign the extracted metadata to the manga object
    creator?.text()?.let { manga.author = it } // Set manga author if available
    description?.text()?.let { manga.description = it } // Set manga description if available

    // Set the chapter name as the title of the book from the metadata
    title?.text()?.let { chapter.name = it }

    // Set the scanlator (publisher or creator) for the chapter
    if (publisher != null) {
        chapter.scanlator = publisher.text()
    } else if (creator != null) {
        chapter.scanlator = creator.text()
    }

    // If a date is available, parse it and set the upload time of the chapter
    if (date != null) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        try {
            // Attempt to parse the date string into a Date object
            val parsedDate = dateFormat.parse(date.text())
            parsedDate?.let {
                chapter.date_upload = it.time // Set the parsed date as the chapter's upload time
            }
        } catch (e: ParseException) {
            // If parsing fails, silently ignore the error (could log if needed)
        }
    }
}