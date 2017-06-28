package nl.koekoek.n2.api;

/**
 * Enum for the different kind of intervals the n2 rest api supports.
 * @author Tom
 *
 */
public enum N2Interval {

    FOUR_HOURS(320), ONE_DAY(1440), ONE_WEEK(10080), ONE_MONTH(43200);

    private int interval;

    private N2Interval(int interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "" + interval;
    }
}
