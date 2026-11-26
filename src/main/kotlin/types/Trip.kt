package types

import com.github.vectura.backend.parser.types.Trip
import utils.getIfExists
import org.apache.commons.csv.CSVRecord

fun CSVRecord.toTrip(): Trip =
    Trip(
        id = this["trip_id"],
        routeId = this["route_id"],
        serviceId = this["service_id"],
        headsign = getIfExists("trip_headsign"),
        name = getIfExists("trip_short_name"),
        block = getIfExists("block_id"),
        shapeId = getIfExists("shape_id")?.takeIf { it.isNotBlank() },
        wheelchairAccessible = getIfExists("wheelchair_accessible")?.toIntOrNull(),
        bikeAccessible = getIfExists("bikes_allowed")?.toIntOrNull()
    )
