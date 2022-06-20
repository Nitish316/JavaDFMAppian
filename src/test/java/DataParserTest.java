import com.opencsv.exceptions.CsvValidationException;
import mining.DataParser;
import models.Event;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DataParserTest {

    @Test
    void shouldParseDataCorrectly() throws CsvValidationException, IOException {
        Pair<List<Event>, List<String>> eventsActivitiesPair = DataParser.parseEventsFromLogs(new File(DataParserTest.class.getResource("/IncidentTest.csv").getPath()));
        assertThat(eventsActivitiesPair.getLeft()).isNotEmpty();
        assertThat(eventsActivitiesPair.getLeft().size()).isEqualTo(8);
        assertThat(eventsActivitiesPair.getLeft().get(0).getTraceId()).isEqualTo("trace_1");
        assertThat(eventsActivitiesPair.getLeft().get(0).getActivity()).isEqualTo("Incident logging");
        assertThat(eventsActivitiesPair.getLeft().get(0).getStart().getYear()).isEqualTo(2016);
        assertThat(eventsActivitiesPair.getLeft().get(0).getStart().getDayOfYear()).isEqualTo(4);
        assertThat(eventsActivitiesPair.getLeft().get(0).getStart().getMonth()).isEqualTo(Month.JANUARY);

        assertThat(eventsActivitiesPair.getRight()).isNotEmpty();
        assertThat(eventsActivitiesPair.getRight().size()).isEqualTo(5);
    }
}
