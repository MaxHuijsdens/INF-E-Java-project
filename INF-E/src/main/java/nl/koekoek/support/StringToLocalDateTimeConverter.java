package nl.koekoek.support;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

/**
 * Custom String to LocalDate converter.
 * 
 * @author Tom
 * 
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    /**
     * String pattern for LocalDateTime conversion.
     */
    public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm";

    @Override
    public LocalDateTime convert(String arg0) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(LOCAL_DATE_TIME_PATTERN);
        return dtf.parseLocalDateTime(arg0);
    }
}
