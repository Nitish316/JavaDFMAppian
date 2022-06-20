package mining.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final public class DateTimeUtils {

    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
    final static DateTimeFormatter filterDateInputFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private DateTimeUtils() {}

    public static LocalDateTime parseTimestamp(String timestamp) {
        return LocalDateTime.parse(timestamp, formatter);
    }

    public static LocalDate parseFilterDateInputTimestamp(String date) {
        return LocalDate.parse(date, filterDateInputFormat);
    }
}
