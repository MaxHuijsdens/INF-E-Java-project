package nl.koekoek.support;

import nl.koekoek.n2.api.N2GraphType;

import org.springframework.core.convert.converter.Converter;

/**
 * Custom string to N2GraphType converter.
 * @author Tom
 *
 */
public class StringToGraphTypeConverter implements Converter<String, N2GraphType> {

    @Override
    public N2GraphType convert(String arg0) {
        return N2GraphType.fromString(arg0);
    }

}
