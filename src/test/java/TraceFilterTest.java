import com.opencsv.exceptions.CsvValidationException;
import mining.DataParser;
import mining.TraceFilter;
import mining.TracesCollector;
import models.Event;
import models.Trace;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TraceFilterTest {

    @Test
    void shouldFilterTracesAsPerDateRange() throws CsvValidationException, IOException {
        Pair<List<Event>, List<String>> eventsActivitiesPair = DataParser
                .parseEventsFromLogs(new File(DataParserTest.class.getResource("/IncidentTest.csv").getPath()));
        Map<String, Trace> traces = TracesCollector.collectTraces(eventsActivitiesPair.getLeft());
        Map<String, Trace> filteredTraces = TraceFilter.filter(traces, LocalDate.of(2016, 1, 4),
                LocalDate.of(2016, 1, 4));
        assertThat(filteredTraces).isNotEmpty();
        assertThat(filteredTraces.size()).isEqualTo(1);
        assertThat(filteredTraces.get("trace_1")).isNotNull();
        assertThat(filteredTraces.get("trace_1").getEvents()).isNotEmpty();
        assertThat(filteredTraces.get("trace_1").getEvents().size()).isEqualTo(4);
    }
}
