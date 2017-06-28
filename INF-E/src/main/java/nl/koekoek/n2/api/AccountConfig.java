package nl.koekoek.n2.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds configuration data.
 * @author Tom
 *
 */
public class AccountConfig {

    //private AccountCredentials accountCredentials;

    private String username;
    private String password;
    private String hostUrl;
    private String authenticationUrl;
    private boolean n2IsUsed;

    /**
     * Default constructor.
     */
    public AccountConfig() {

    }

    /**
     * Constructor for creating a AccountConfig object.
     * @param username The username of an n2 account.
     * @param password The password of an n2 account.
     * @param baseUrl The baseUrl of the client.
     * @param authenticationUrl The authenticationUrl of the client.
     */
    public AccountConfig(String username, String password, String hostUrl, String authenticationUrl) {
        //create and fill the accountCredentials map
        this.username = username;
        this.password = password;
        this.hostUrl = hostUrl;
        this.authenticationUrl = authenticationUrl;
    }

    /**
     * Get the accountcredentials as a map with username and password key.
     * @return Map<String, String>
     */
    public Map<String, String> getAccountCredentialsAsMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", this.getUsername());
        map.put("password", this.getPassword());
        return map;
    }

    public String getAuthenticationUrl() {
        return authenticationUrl;
    }

    public void setAuthenticationUrl(String authenticationUrl) {
        this.authenticationUrl = authenticationUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public boolean isN2IsUsed() {
        return n2IsUsed;
    }

    public void setN2IsUsed(boolean n2IsUsed) {
        this.n2IsUsed = n2IsUsed;
    }

}
