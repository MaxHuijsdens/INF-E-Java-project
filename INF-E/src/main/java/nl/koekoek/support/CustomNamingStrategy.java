package nl.koekoek.support;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Custom naming strategy for hibernate.
 * @author Tom
 *
 */
@SuppressWarnings("serial")
public class CustomNamingStrategy extends ImprovedNamingStrategy {

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        return super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName) + "_id";
    }
}
