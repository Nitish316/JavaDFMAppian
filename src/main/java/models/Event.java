package models;

import java.time.LocalDateTime;

public class Event {
  String traceId;
  String activity;
  LocalDateTime start;

  public Event(String traceId, String activity, LocalDateTime start) {
    this.traceId = traceId;
    this.activity = activity;
    this.start = start;
  }

  public String getTraceId() {
    return traceId;
  }

  public String getActivity() {
    return activity;
  }

  public LocalDateTime getStart() {
    return start;
  }
}
