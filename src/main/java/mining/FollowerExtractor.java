package mining;

import models.Event;
import models.Trace;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FollowerExtractor {

    private FollowerExtractor() {}

    public static Map<String, Map<String, Integer>> extractFollowers(final Map<String, Trace> traces) {
        if (traces == null) return Collections.emptyMap();
        final Map<String, Map<String, Integer>> followerMap = new HashMap<>();

        traces.entrySet().forEach(entry -> populateFollowers(followerMap, entry));
        return followerMap;
    }

    private static void populateFollowers(final Map<String, Map<String, Integer>> followerMap,
                                   final Map.Entry<String, Trace> entry) {
        if (isEmpty(entry)) return;

        final List<Event> events = entry.getValue().getEvents();
        for (int i = 0;i < events.size() - 1;i++) {
            final String initialActivity = events.get(i).getActivity();
            if (!followerMap.containsKey(initialActivity)) {
                followerMap.put(initialActivity, new HashMap<>());
            }

            final String followerActivity = events.get(i + 1).getActivity();
            followerMap.get(initialActivity).put(followerActivity,
                    followerMap.get(initialActivity).getOrDefault(followerActivity, 0) + 1);
        }
    }

    private static boolean isEmpty(final Map.Entry<String, Trace> entry) {
        return entry == null
                || entry.getValue() == null
                || entry.getValue().getEvents() == null
                || entry.getValue().getEvents().isEmpty();
    }
}
