package com.loopco.pricesolution.utils;

import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateUtils {

    public static final DateTimeFormatter DATE_TIME_FORMATTER_READER = DateTimeFormatter.ofPattern(""
            + "[dd-MM-yyyy HH:mm:ss]"
            + "[dd-MM-yyyy'T'HH:mm:ss]"
            + "[dd/MM/yyyy HH:mm:ss]"
            + "[dd/MM/yyyy'T'HH:mm:ss]"
            + "[yyyy[-][/]MM[-][/]dd['T'][ ]HH:mm[:][.]ss"
    );

    public static LocalDateTime toLocalDateTime(@NonNull String date) throws DateTimeParseException {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER_READER);
    }

}
