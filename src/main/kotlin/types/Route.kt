package types

import com.github.vectura.backend.parser.types.Route
import utils.getIfExists
import org.apache.commons.csv.CSVRecord

fun CSVRecord.toRoute(): Route =
    Route(
        id = this["route_id"],
        // agency_id is optional in GTFS; missing column or blank cell -> null.
        agency = getIfExists("agency_id")?.takeIf { it.isNotBlank() },
        longName = getIfExists("route_long_name"),
        shortName = getIfExists("route_short_name"),
        description = getIfExists("route_desc"),
        // GTFS requires route_type, but some feeds leave it blank; default to 3 (bus).
        type = getIfExists("route_type")?.toIntOrNull() ?: 3,
        link = getIfExists("route_url"),
        color = getIfExists("route_color"),
        textColor = getIfExists("route_text_color")
    )
