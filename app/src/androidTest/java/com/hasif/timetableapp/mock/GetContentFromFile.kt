package com.hasif.timetableapp.mock

import androidx.test.platform.app.InstrumentationRegistry
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun getContentFromFile(name: String): String {
    return buildString {
        try {
            val reader = BufferedReader(
                InputStreamReader(
                    InstrumentationRegistry.getInstrumentation().context.assets.open(name),
                    "UTF-8"
                )
            )
            reader.forEachLine { append(it) }
        } catch (ignore: IOException) {
            ignore.printStackTrace()
        }
    }
}