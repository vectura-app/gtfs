package types

import com.github.vectura.backend.parser.types.Shape
import utils.getIfExists
import org.apache.commons.csv.CSVRecord

fun CSVRecord.toShape(): Shape =
    Shape(
        id = this["shape_id"],
        latitude = this["shape_pt_lat"].toDouble(),
        longitude = this["shape_pt_lon"].toDouble(),
        sequence = this["shape_pt_sequence"].toInt(),
        distanceTraveled = getIfExists("shape_dist_traveled")?.toFloatOrNull()
    )
