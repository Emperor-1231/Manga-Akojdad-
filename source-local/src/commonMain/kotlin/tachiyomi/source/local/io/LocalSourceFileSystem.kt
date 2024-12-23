package com.mangaakojdad.source.local.io

import com.hippo.unifile.UniFile

expect class LocalSourceFileSystem {

    // الحصول على المجلد الأساسي
    fun getBaseDirectory(): UniFile?

    // الحصول على الملفات داخل المجلد الأساسي
    fun getFilesInBaseDirectory(): List<UniFile>

    // الحصول على مجلد المانجا بالاسم المحدد
    fun getMangaDirectory(name: String): UniFile?

    // الحصول على الملفات داخل مجلد المانجا
    fun getFilesInMangaDirectory(name: String): List<UniFile>
}