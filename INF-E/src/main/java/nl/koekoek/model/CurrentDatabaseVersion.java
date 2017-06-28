/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Current database version.
 *
 * @author Jeroen van Schagen
 * @since Aug 25, 2014
 */
@Entity
public class CurrentDatabaseVersion {

    @Id
    private String id;

    private String tag;

    private String author;

    private Date dateExecuted;

    private String fileName;

    private String comments;

    public String getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDateExecuted() {
        return dateExecuted;
    }

    public String getFileName() {
        return fileName;
    }

    public String getComments() {
        return comments;
    }

}
