package nl.koekoek.n2.model;

import java.util.List;

/**
 * Class representing an event log of a n2 host.
 * @author Tom
 *
 */
public class N2HostEventLog {

    private List<N2HostEvent> eventLog;

    /**
     * Default constructor.
     */
    public N2HostEventLog() {

    }

    public List<N2HostEvent> getEventLog() {
        return eventLog;
    }

    public void setEventLog(List<N2HostEvent> eventLog) {
        this.eventLog = eventLog;
    }

}
