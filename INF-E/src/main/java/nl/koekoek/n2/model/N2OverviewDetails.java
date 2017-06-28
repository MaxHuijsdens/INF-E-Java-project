package nl.koekoek.n2.model;

/**
 * Class that contains a HostOverview and a HostDetail object of a n2 host.
 * @author Tom
 *
 */
public class N2OverviewDetails {

    private N2HostOverview overview;
    private N2HostDetail detail;

    /**
     * Default constructor.
     */
    public N2OverviewDetails() {

    }

    public N2HostOverview getOverview() {
        return overview;
    }

    public void setOverview(N2HostOverview overview) {
        this.overview = overview;
    }

    public N2HostDetail getDetail() {
        return detail;
    }

    public void setDetail(N2HostDetail detail) {
        this.detail = detail;
    }
}
