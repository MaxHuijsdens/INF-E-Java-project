package nl.koekoek.support;

import java.io.IOException;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * @author Tom
 *
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(StringToLocalDateTimeConverter.LOCAL_DATE_TIME_PATTERN);
        arg1.writeString(dtf.print(arg0));
    }

}
