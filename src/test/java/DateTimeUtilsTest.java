import mining.utils.DateTimeUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeUtilsTest {

    @Test
    void shouldParseValidTimestamp() {
        String timestamp = "2016/01/05 03:56:44.000";
        LocalDateTime localDateTime = DateTimeUtils.parseTimestamp(timestamp);
        assertThat(localDateTime).isNotNull();
        assertThat(localDateTime.getMonth()).isEqualTo(Month.JANUARY);
        assertThat(localDateTime.getYear()).isEqualTo(2016);
        assertThat(localDateTime.getDayOfYear()).isEqualTo(5);
        assertThat(localDateTime.getHour()).isEqualTo(3);
        assertThat(localDateTime.getMinute()).isEqualTo(56);
        assertThat(localDateTime.getSecond()).isEqualTo(44);
    }
}
