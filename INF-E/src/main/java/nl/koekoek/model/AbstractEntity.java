package nl.koekoek.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.jarbframework.constraint.metadata.DatabaseGenerated;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Base model class that holds the primary key.
 * @author Tom
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
@JsonIgnoreProperties({ "new" })
public class AbstractEntity implements Persistable<Long> {

    @Id
    @DatabaseGenerated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public boolean isNew() {
        return null == id;
    }

}
