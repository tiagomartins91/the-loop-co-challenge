package com.loopco.pricesolution.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DateUtilsTest {


    @Test
    void must_convert_to_local_date_time() {

        final LocalDateTime dateTime = LocalDateTime.of(2021, 7, 24, 10, 8, 59);
        LocalDateTime localDateTime = DateUtils.toLocalDateTime("2021-07-24 10:08:59");
        assertNotNull(localDateTime);
        assertEquals(dateTime, localDateTime);

        localDateTime = DateUtils.toLocalDateTime("2021-07-24T10:08:59");
        assertNotNull(localDateTime);
        assertEquals(dateTime, localDateTime);

        localDateTime = DateUtils.toLocalDateTime("24-07-2021 10:08:59");
        assertNotNull(localDateTime);
        assertEquals(dateTime, localDateTime);

        localDateTime = DateUtils.toLocalDateTime("24-07-2021T10:08:59");
        assertNotNull(localDateTime);
        assertEquals(dateTime, localDateTime);

        localDateTime = DateUtils.toLocalDateTime("24/07/2021 10:08:59");
        assertNotNull(localDateTime);
        assertEquals(dateTime, localDateTime);

        localDateTime = DateUtils.toLocalDateTime("24/07/2021T10:08:59");
        assertNotNull(localDateTime);
        assertEquals(dateTime, localDateTime);

        Assertions.assertThrows(DateTimeParseException.class, () -> DateUtils.toLocalDateTime("24-14-2021 10:08:59"));
    }
}
