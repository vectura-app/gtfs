import com.github.vectura.backend.parser.Configurator
import com.github.vectura.backend.parser.ParserInterface
import com.github.vectura.backend.parser.types.Agency
import com.github.vectura.backend.parser.types.Calendar
import com.github.vectura.backend.parser.types.CalendarDate
import com.github.vectura.backend.parser.types.Route
import com.github.vectura.backend.parser.types.Shape
import com.github.vectura.backend.parser.types.Stop
import com.github.vectura.backend.parser.types.StopTime
import com.github.vectura.backend.parser.types.Trip
import com.github.vectura.parser.gtfs.utils.openReader
import types.toAgency
import types.toCalendar
import types.toCalendarDate
import types.toRoute
import types.toShape
import types.toStop
import types.toStopTime
import types.toTrip
import java.io.File
import java.net.URL
import java.util.zip.ZipFile

class GTFSModule: ParserInterface{
    private lateinit var tmp: File
    private lateinit var zip: ZipFile

    override fun configureModule(configurator: Configurator) {
        tmp = File.createTempFile("vectura_gtfs_", ".tmp")
        URL(configurator.url).openStream().buffered().use { input ->
            tmp.outputStream().use { output -> input.copyTo(output) }
        }
        zip = ZipFile(tmp)
    }

    override fun cleanUp() {
        zip.close()
        tmp.delete()
    }

    override fun getAgencies(): Sequence<Agency> = sequence {
        openReader(zip, "agency.txt")?.use { records ->
            records.forEach { record ->
                yield(record.toAgency())
            }
        } ?: error("Required file agency.txt was not found")
    }

    override fun getCalendars(): Sequence<Calendar> = sequence {
        openReader(zip, "calendar.txt")?.use { records ->
            records.forEach { record ->
                yield(record.toCalendar())
            }
        }
    }

    override fun getCalendarDates(): Sequence<CalendarDate> = sequence {
        openReader(zip, "calendar_dates.txt")?.use { records ->
            records.forEach { record ->
                yield(record.toCalendarDate())
            }
        }
    }

    override fun getStops(): Sequence<Stop> = sequence {
        openReader(zip, "stops.txt")?.use { records ->
            records.forEach { record ->
                yield(record.toStop())
            }
        }
    }

    override fun getRoutes(): Sequence<Route> = sequence {
        openReader(zip, "routes.txt")?.use { records ->
            records.forEach { record ->
                yield(record.toRoute())
            }
        } ?: error("Required file routes.txt was not found")
    }

    override fun getShapes(): Sequence<Shape> = sequence {
        openReader(zip, "shapes.txt")?.use { records ->
            records.forEach { record ->
                yield(record.toShape())
            }
        }
    }

    override fun getStopTimes(): Sequence<StopTime> = sequence {
        openReader(zip, "stop_times.txt")?.use { records ->
            records.forEach { record ->
                yield(record.toStopTime())
            }
        } ?: error("Required file stop_times.txt was not found")
    }

    override fun getTrips(): Sequence<Trip> = sequence {
        openReader(zip, "trips.txt")?.use { records ->
            records.forEach { record ->
                yield(record.toTrip())
            }
        } ?: error("Required file trips.txt was not found")
    }
}
