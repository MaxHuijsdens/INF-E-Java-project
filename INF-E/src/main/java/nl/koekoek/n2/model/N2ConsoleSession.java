package nl.koekoek.n2.model;

/**
 * Class representing a console session of a n2 host.
 * @author Tom
 *
 */
public class N2ConsoleSession {

    private String remoteHost;
    private String userName;

    /**
     * Default constructor.
     */
    public N2ConsoleSession() {

    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "remote host = " + this.remoteHost + ", user name = " + this.userName;
    }

}
