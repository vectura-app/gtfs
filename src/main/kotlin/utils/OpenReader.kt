package com.github.vectura.parser.gtfs.utils

import org.apache.commons.csv.CSVFormat
import org.apache.commons.io.input.BOMInputStream
import java.io.InputStreamReader
import java.util.zip.ZipFile

fun openReader(zip: ZipFile, file: String) =
    zip.getEntry(file)?.let { entry ->
        CSVFormat.DEFAULT
            .builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .setIgnoreHeaderCase(true)
            .setTrim(true)
            .get()
            .parse(
                InputStreamReader(BOMInputStream.builder().setInputStream(zip.getInputStream(entry)).get())
            )
    }