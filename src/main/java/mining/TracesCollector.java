package mining;

import models.Event;
import models.Trace;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class TracesCollector {

    private TracesCollector() {}

    public static Map<String, Trace> collectTraces(List<Event> eventList) {
        if (eventList == null || eventList.isEmpty()) return Collections.emptyMap();

        return eventList.stream()
                .collect(Collectors.groupingBy(Event::getTraceId))
                .entrySet().stream()
                .map(entry -> new Trace(entry.getKey(), entry.getValue().stream()
                        .sorted(Comparator.comparing(Event::getStart))
                        .collect(Collectors.toList())))
                .collect(Collectors.toMap(Trace::getTraceId, trace -> trace));
    }
}
