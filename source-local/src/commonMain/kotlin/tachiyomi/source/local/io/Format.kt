package com.mangaakojdad.source.local.io

import com.hippo.unifile.UniFile
import tachiyomi.core.common.storage.extension
import com.mangaakojdad.source.local.io.Archive.isSupported as isArchiveSupported

sealed interface Format {
    // تنسيق المجلد
    data class Directory(val file: UniFile) : Format

    // تنسيق الأرشيف
    data class Archive(val file: UniFile) : Format

    // تنسيق epub
    data class Epub(val file: UniFile) : Format

    // استثناء التنسيق غير المعروف
    class UnknownFormatException : Exception()

    companion object {

        // تحديد نوع الملف
        fun valueOf(file: UniFile) = when {
            file.isDirectory -> Directory(file)
            file.extension.equals("epub", true) -> Epub(file)
            isArchiveSupported(file) -> Archive(file)
            else -> throw UnknownFormatException()
        }
    }
}