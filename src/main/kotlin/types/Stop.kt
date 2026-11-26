package types

import com.github.vectura.backend.parser.types.Stop
import utils.getIfExists
import org.apache.commons.csv.CSVRecord

fun CSVRecord.toStop(): Stop =
    Stop(
        id = this["stop_id"],
        code = getIfExists("stop_code"),
        name = this["stop_name"],
        description = getIfExists("stop_desc"),
        link = getIfExists("stop_url"),
        lat = this["stop_lat"].toDouble(),
        long = this["stop_lon"].toDouble(),
        zoneId = getIfExists("zone_id"),
        type = getIfExists("location_type")?.toIntOrNull(),
        parentStation = getIfExists("parent_station"),
        wheelchairBoarding = getIfExists("wheelchair_boarding")?.toIntOrNull(),
        platformCode = getIfExists("platform_code")
    )
