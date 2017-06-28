package nl.koekoek.n2.model;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Class representing an event occuring on a n2 host.
 * @author Tom
 *
 */
public class N2HostEvent {

    private String timeStamp;
    private String status;
    private String[] problems;
    private String text;

    /**
     * Default constructor.
     */
    public N2HostEvent() {

    }

    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Get the timestamp as DateTime Object.
     * @return DateTime Object.
     */
    public DateTime getTimeStampAsDateTime() {
        DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        return dateStringFormat.parseDateTime(this.timeStamp);
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getProblems() {
        return problems;
    }

    public void setProblems(String[] newProblems) {
        if (newProblems == null) {
            this.problems = new String[0];
        } else {
            this.problems = Arrays.copyOf(newProblems, newProblems.length);
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("timestamp = " + this.timeStamp + ", status  = " + this.status + ", problems = ");
        for (String s : this.problems) {
            result.append(s);
            result.append(", ");
        }
        result.append("text = " + this.text);
        return result.toString();
    }
}
