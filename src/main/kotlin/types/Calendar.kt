package types

import com.github.vectura.backend.parser.types.Calendar
import utils.GTFSFormat
import org.apache.commons.csv.CSVRecord

fun CSVRecord.toCalendar(): Calendar =
    Calendar(
        id = this["service_id"],
        monday = this["monday"].toInt(),
        tuesday = this["tuesday"].toInt(),
        wednesday = this["wednesday"].toInt(),
        thursday = this["thursday"].toInt(),
        friday = this["friday"].toInt(),
        saturday = this["saturday"].toInt(),
        sunday = this["sunday"].toInt(),
        startDate = GTFSFormat.parse(this["start_date"]),
        endDate = GTFSFormat.parse(this["end_date"])
    )
