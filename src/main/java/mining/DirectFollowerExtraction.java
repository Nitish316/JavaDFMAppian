package mining;

import models.Event;
import models.Trace;
import mining.utils.DateTimeUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Extracts the direct follower matrix from a log.
 * Parses each line of the log file as an `Event` and builds the `Trace`s from the events.
 * Then counts all direct follower relations and prints them to the command line.
 */
public class DirectFollowerExtraction {

  public static void main(String[] args) throws Exception {

    // Get the log file from the resources
    File logFile = new File(DirectFollowerExtraction.class.getResource("/IncidentExample.csv").getPath());

    System.out.println("Please use the events in the log-file located at " + logFile.getAbsolutePath() +
        " to build a direct follower matrix.");

    /////////////////////////////
    /// YOUR WORK STARTS HERE ///
    /////////////////////////////
    final LocalDate startDate = args.length > 0? DateTimeUtils.parseFilterDateInputTimestamp(args[0]): LocalDate.MIN;
    final LocalDate endDate = args.length > 1? DateTimeUtils.parseFilterDateInputTimestamp(args[1]): LocalDate.MAX;

    final Pair<List<Event>, List<String>> eventsActivitiesPair = DataParser.parseEventsFromLogs(logFile);
    final Map<String, Trace> traces = TracesCollector.collectTraces(eventsActivitiesPair.getLeft());
    final Map<String, Trace> filteredTraces = TraceFilter.filter(traces, startDate, endDate);
    final Map<String, Map<String, Integer>> followersMatrix = FollowerExtractor.extractFollowers(filteredTraces);
    printFollowerMatrix(followersMatrix, eventsActivitiesPair.getRight());
  }

  private static void printFollowerMatrix(final Map<String, Map<String, Integer>> followersMatrix, final List<String> activities) {
    for (String activity: activities) {
      System.out.print(activity.concat("   |   "));
    }
    System.out.print("\n");
    for (String activityInitial: activities) {
      System.out.print("\n");
      System.out.print(activityInitial.concat("   |   "));
      for (String activityFollower: activities) {
        if (followersMatrix.containsKey(activityInitial)) {
          System.out.print(followersMatrix.get(activityInitial).getOrDefault(activityFollower, 0) + "   |   ");
        } else {
          System.out.print("0   |   ");
        }
      }
    }
  }
}
