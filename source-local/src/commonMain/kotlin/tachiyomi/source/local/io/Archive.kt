package com.mangaakojdad.source.local.io

import com.hippo.unifile.UniFile
import tachiyomi.core.common.storage.extension

object Archive {

    // أنواع الأرشيف المدعومة
    private val SUPPORTED_ARCHIVE_TYPES = listOf("zip", "cbz", "rar", "cbr", "7z", "cb7", "tar", "cbt")

    // التحقق مما إذا كان الملف من نوع أرشيف مدعوم
    fun isSupported(file: UniFile): Boolean {
        return file.extension?.lowercase() in SUPPORTED_ARCHIVE_TYPES
    }
}