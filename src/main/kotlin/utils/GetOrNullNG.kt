package utils

import org.apache.commons.csv.CSVRecord

fun CSVRecord.getIfExists(column: String): String? = // TODO: Abolish this bullshit and move it to the gtfs repo making backend universal
    if (isMapped(column)) get (column) else null

//TODO: Add a method that will auto parse to int and give a log warning when its parsed to null