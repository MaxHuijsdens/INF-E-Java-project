package nl.koekoek.support;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Custom object mapper for json.
 * @author Tom
 *
 */
public class CustomObjectMapper extends ObjectMapper {

    /**
     * Default Constructor.
     */
    public CustomObjectMapper() {
        super();

        SimpleModule module = new SimpleModule("KoekoekModule");
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        registerModule(module);
    }

}
