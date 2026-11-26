package types

import com.github.vectura.backend.parser.types.Agency
import utils.getIfExists
import org.apache.commons.csv.CSVRecord

fun CSVRecord.toAgency(): Agency =
    Agency(
        id = getIfExists("agency_id"),
        name = this["agency_name"],
        url = this["agency_url"],
        timezone = this["agency_timezone"],
        lang = getIfExists("agency_lang"),
        phone = getIfExists("agency_phone"),
        fareUrl = getIfExists("agency_fare_url"),
        email = getIfExists("agency_email")
    )
