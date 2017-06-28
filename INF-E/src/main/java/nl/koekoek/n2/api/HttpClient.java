package nl.koekoek.n2.api;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Client for executing http requests.
 * @author Tom
 *
 */
public class HttpClient {

    private CloseableHttpClient httpClient;
    private BasicCookieStore cookieStore;
    private AccountConfig accountConfig;

    /**
     * Default Constructor.
     * @param accountConfig AccountConfig object that the client uses for authentication information and baseurl
     */
    public HttpClient(AccountConfig accountConfig) {
        this.accountConfig = accountConfig;
        this.cookieStore = new BasicCookieStore();
        this.httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
    }

    /**
     * Executes a login request to get a cookie with a session id.
     * @return HttpResponse Object.
     */
    public CloseableHttpResponse authenticate() {
        HttpUriRequest login = createUriRequest(this.accountConfig.getAuthenticationUrl(), RequestType.POST,
                this.accountConfig.getAccountCredentialsAsMap());
        return executeRequest(login);
    }

    /**
     * Executes an get request.
     * @param path the path where you would like to make a request to.
     * @return CloseableHttpResponse object.
     */
    public CloseableHttpResponse executeHttpGetUrl(String path) {
        //LOGGER.debug(checkForSession());
        if (!checkForSession()) {
            authenticate();
        }
        HttpUriRequest getRequest = createUriRequest(this.accountConfig.getHostUrl() + path, RequestType.GET, null);
        return this.executeRequest(getRequest);
    }

    private CloseableHttpResponse executeRequest(HttpUriRequest request) {
        //LOGGER.info("executing http request: " + request.getRequestLine());
        try {
            CloseableHttpResponse response = this.httpClient.execute(request);
            System.out.println("Request:" + request.getRequestLine() + " Statusline: " + response.getStatusLine());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN) {
                System.out.println("Request:" + request.getRequestLine() + " resulted in forbidden");
            }
            return response;
        } catch (ClientProtocolException e) {
            System.out.println("Client protocol exception, message: " + e.getLocalizedMessage() + ", on request:" + request.getRequestLine());
        } catch (IOException e) {
            System.out.println("IO exception, message: " + e.getLocalizedMessage() + ", on request:" + request.getRequestLine());
        }
        return null;
    }

    /**
     * Creates an HttpUriRequest.
     * @param url The url you want to access.
     * @param type The type of request you want to make. Example: RequestType.GET
     * @param params Map of parameters you want to give with the request.
     * @return HttpUriRequest object.
     */
    private HttpUriRequest createUriRequest(String url, RequestType type, Map<String, String> params) {
        RequestBuilder requestBuilder = null;

        if (type == RequestType.POST) {
            requestBuilder = RequestBuilder.post().setUri(url);
        } else {
            requestBuilder = RequestBuilder.get().setUri(url);
        }

        if (requestBuilder != null && params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                requestBuilder.addParameter(entry.getKey(), entry.getValue());
            }
            //            for (String s : params.keySet()) {
            //                requestBuilder.addParameter(s, params.get(s));
            //            }
        }

        return requestBuilder.build();
    }

    /**
     * Checks if the cookie with the session is still available.
     * @return returns true if session is available, false if not.
     */
    private boolean checkForSession() {
        clearExpiredCookies();
        if (!this.cookieStore.getCookies().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Remove cookies from the cookiestore if they are expired.
     */
    private void clearExpiredCookies() {
        Date now = new Date();
        this.cookieStore.clearExpired(now);
    }

    /**
     * Closes the client.
     * @throws IOException
     */
    public void close() throws IOException {
        this.cookieStore.clear();
        this.httpClient.close();
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public BasicCookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(BasicCookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public AccountConfig getAccountConfig() {
        return accountConfig;
    }

    public void setAccountConfig(AccountConfig accountConfig) {
        this.accountConfig = accountConfig;
    }

}
