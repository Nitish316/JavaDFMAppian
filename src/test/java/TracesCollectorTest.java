import com.opencsv.exceptions.CsvValidationException;
import mining.DataParser;
import mining.TracesCollector;
import models.Event;
import models.Trace;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TracesCollectorTest {

    @Test
    void shouldCollectTracesWithSameId() throws CsvValidationException, IOException {
        Pair<List<Event>, List<String>> eventsActivitiesPair = DataParser
                .parseEventsFromLogs(new File(DataParserTest.class.getResource("/IncidentTest.csv").getPath()));
        Map<String, Trace> traces = TracesCollector.collectTraces(eventsActivitiesPair.getLeft());
        assertThat(traces).isNotEmpty();
        assertThat(traces.size()).isEqualTo(2);
        assertThat(traces.get("trace_0").getEvents()).isNotEmpty();
        assertThat(traces.get("trace_0").getEvents().size()).isEqualTo(4);
        assertThat(traces.get("trace_0").getEvents().get(0).getActivity()).isEqualTo("Incident logging");
        assertThat(traces.get("trace_0").getEvents().get(1).getActivity()).isEqualTo("Investigation and diagnosis");
        assertThat(traces.get("trace_0").getEvents().get(2).getActivity()).isEqualTo("Resolution and recovery");
        assertThat(traces.get("trace_0").getEvents().get(3).getActivity()).isEqualTo("Incident closure");
    }
}
