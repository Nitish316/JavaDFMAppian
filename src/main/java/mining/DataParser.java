package mining;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import models.Event;
import mining.utils.DateTimeUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class DataParser {

    private DataParser() {}

    public static Pair<List<Event>, List<String>> parseEventsFromLogs(final File logFile) throws IOException, CsvValidationException {
        final List<Event> events = new ArrayList<>();
        final List<String> uniqueActivities = new ArrayList<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(logFile)).withSkipLines(1).build()) {
            String[] rowElements;
            while ((rowElements = reader.readNext()) != null) {
                events.add(new Event(rowElements[0], rowElements[1], DateTimeUtils.parseTimestamp(rowElements[2])));
                if (!uniqueActivities.contains(rowElements[1])) {
                    uniqueActivities.add(rowElements[1]);
                }
            }
        }
        return Pair.of(events, uniqueActivities);
    }
}
