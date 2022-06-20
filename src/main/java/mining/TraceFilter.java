package mining;

import models.Trace;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public final class TraceFilter {

    private TraceFilter() {}

    public static Map<String, Trace> filter(final Map<String, Trace> traces,final LocalDate startDate, final LocalDate endDate) {
        if (traces == null) return null;

        return traces.entrySet().stream()
                .filter(stringTraceEntry -> stringTraceEntry.getValue().getEvents()
                        .get(0).getStart()
                        .compareTo(startDate.atStartOfDay()) > 0)
                .filter(stringTraceEntry -> stringTraceEntry.getValue().getEvents()
                        .get(stringTraceEntry.getValue().getEvents().size() - 1).getStart()
                        .compareTo(endDate.plusDays(1).atStartOfDay()) < 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
