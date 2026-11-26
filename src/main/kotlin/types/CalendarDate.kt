package types

import com.github.vectura.backend.parser.types.CalendarDate
import utils.GTFSFormat
import org.apache.commons.csv.CSVRecord

fun CSVRecord.toCalendarDate(): CalendarDate =
    CalendarDate(
        id = this["service_id"],
        date = GTFSFormat.parse(this["date"]),
        type = this["exception_type"].toInt()
    )
