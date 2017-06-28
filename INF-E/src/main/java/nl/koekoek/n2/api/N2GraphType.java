package nl.koekoek.n2.api;

/**
 * Enum for the different kind of graph data you can get back.
 * @author Tom
 *
 */
public enum N2GraphType {

    NETWORK_IN("netin"), NETWORK_OUT("netout"), LOAD("load"), CPU("cpu"), DISK_IO("diskio"), RAM("ram"), SWAP("swap"),
    ROUND_TRIP_TIME("rtt"), TOTAL_MEMORY("totalmem"), NUMBER_OF_PROCESSES("nproc"), IO_WAIT("iowait");

    private String urlName;

    /**
     * Constructor for N2GraphType enum.
     * @param urlName the name used in the url when fetching specific graphdata.
     */
    private N2GraphType(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return this.urlName;
    }

    /**
     * Returns N2GraphType from the enum name or the url name.
     * @param text The string you want to convert to a N2GraphType.
     * @return N2GraphType object
     */
    public static N2GraphType fromString(String text) {
        if (null != text) {
            for (N2GraphType graphType : N2GraphType.values()) {
                if (graphType.toString().equalsIgnoreCase(text)) {
                    return graphType;
                }
            }
        }
        return null;
    }
}
