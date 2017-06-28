package nl.koekoek.n2.model;

import java.util.List;

/**
 * Holds a list of OverviewDetails of a all n2 hosts.
 * @author Tom
 *
 */
public class N2OverviewDetailsWrapper {

    List<N2OverviewDetails> collection;

    /**
     * Default constructor.
     */
    public N2OverviewDetailsWrapper() {

    }

    public List<N2OverviewDetails> getCollection() {
        return collection;
    }

    public void setCollection(List<N2OverviewDetails> collection) {
        this.collection = collection;
    }

}
