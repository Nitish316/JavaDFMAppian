import com.opencsv.exceptions.CsvValidationException;
import mining.DataParser;
import mining.FollowerExtractor;
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

public class FollowerExtractorTest {

    @Test
    void shouldExtractValidFollowers() throws CsvValidationException, IOException {
        Pair<List<Event>, List<String>> eventsActivitiesPair = DataParser
                .parseEventsFromLogs(new File(DataParserTest.class.getResource("/IncidentTest.csv").getPath()));
        Map<String, Trace> traces = TracesCollector.collectTraces(eventsActivitiesPair.getLeft());
        Map<String, Map<String, Integer>> followersMatrix = FollowerExtractor.extractFollowers(traces);

        assertThat(followersMatrix).isNotEmpty();
        assertThat(followersMatrix.containsKey("Incident logging")).isTrue();
        assertThat(followersMatrix.get("Incident logging").size()).isEqualTo(2);
        assertThat(followersMatrix.get("Incident logging")
                .getOrDefault("Investigation and diagnosis", 0)).isEqualTo(1);
        assertThat(followersMatrix.get("Incident logging")
                .getOrDefault("Initial diagnosis", 0)).isEqualTo(1);
        assertThat(followersMatrix.get("Incident logging")
                .getOrDefault("Resolution and recovery", 0)).isEqualTo(0);
    }
}
