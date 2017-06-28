package nl.koekoek.n2.model;

/**
 * Class that represents a network port on a n2 host.
 * @author Tom
 *
 */
public class N2NetworkPort {

    private int connectedState;
    private int otherState;

    /**
     * Default constructor.
     */
    public N2NetworkPort() {

    }

    public int getConnectedState() {
        return connectedState;
    }

    public void setConnectedState(int connectedState) {
        this.connectedState = connectedState;
    }

    public int getOtherState() {
        return otherState;
    }

    public void setOtherState(int otherState) {
        this.otherState = otherState;
    }

    @Override
    public String toString() {
        return "connected state = " + this.connectedState + ", other state = " + this.otherState;
    }
}
