package com.mangaakojdad.source.local.image

import com.hippo.unifile.UniFile
import eu.kanade.tachiyomi.source.model.SManga
import java.io.InputStream

expect class LocalCoverManager {

    // البحث عن صورة الغلاف باستخدام رابط المانجا
    fun find(mangaUrl: String): UniFile?

    // تحديث صورة الغلاف لمانجا معينة
    fun update(manga: SManga, inputStream: InputStream): UniFile?
}