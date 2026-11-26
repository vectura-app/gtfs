package types

import com.github.vectura.backend.parser.types.StopTime
import utils.getIfExists
import org.apache.commons.csv.CSVRecord

fun CSVRecord.toStopTime(): StopTime =
    StopTime(
        tripId = this["trip_id"],
        arrivalTime = this["arrival_time"],
        departureTime = this["departure_time"],
        stopId = this["stop_id"],
        stopSequence = this["stop_sequence"].toInt(),
        stopHeadsign = getIfExists("stop_headsign"),
        pickupType = getIfExists("pickup_type")?.toIntOrNull(),
        dropOffType = getIfExists("drop_off_type")?.toIntOrNull(),
        shapeDistanceTraveled = getIfExists("shape_dist_traveled")?.toFloatOrNull(),
        timepoint = getIfExists("timepoint")?.toIntOrNull()
    )
