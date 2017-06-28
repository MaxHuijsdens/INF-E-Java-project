package nl.koekoek.n2.api;

import java.io.IOException;
import java.util.List;

import nl.koekoek.n2.model.N2GraphData;
import nl.koekoek.n2.model.N2HostDetail;
import nl.koekoek.n2.model.N2HostDetailAverage;
import nl.koekoek.n2.model.N2HostEventLog;
import nl.koekoek.n2.model.N2HostOverview;
import nl.koekoek.n2.model.N2OverviewDetailsWrapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class that returns java objects from json data that it retrieves from the n2 rest api.
 * @author Tom
 *
 */
public class N2DataFetcher {

    private static final Logger LOGGER = Logger.getRootLogger();
    private static final String DATE_URL = "/date/";
    private static final String STARTDATE_URL = "/startdate/";
    private static final String ENDDATE_URL = "/enddate/";
    private static final String GRAPH_URL = "/graph/";
    private static final String INTERVAL_URL = "/interval/";
    private static final String EVENTLOG_URL = "/eventlog";
    private static final String DETAILS_URL = "/details";
    private static final String AVERAGE_URL = "/average/";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm";

    //client for the http requests
    private HttpClient client;
    //Mapper for mapping the json to java objects
    private ObjectMapper objectMapper;
    private DateTimeFormatter dateTimeFormatter;

    /**
     * Default constructor.
     * @param client HttpClient object needed for executing requests.
     */
    public N2DataFetcher(HttpClient client) {
        this.client = client;
        this.objectMapper = new ObjectMapper();
        this.dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT);
        //configure the deserialization of the object mapper.
        this.objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        this.objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    /**
     * Login with an n2 account.
     * @return 
     */
    public CloseableHttpResponse login() {
        return this.client.authenticate();
    }

    /**
     * Logout the current logged in account.
     */
    public void logout() {
        this.client.getCookieStore().clear();
    }

    /**
     * Fetches general information of all hosts that are linked to the logged in account.
     * @return List with N2HostOverview objects.
     */
    public List<N2HostOverview> fetchAllHosts() {
        return this.responseToObject(this.client.executeHttpGetUrl(""), new TypeReference<List<N2HostOverview>>() {
        });
    }

    /**
     * Fetches a single host.
     * @param ipaddress The ip address of the host you want to fetch.
     * @return N2HostOverview object or null if the host was not found.
     */
    public N2HostOverview fetchHost(String ipaddress) {
        List<N2HostOverview> hosts = fetchAllHosts();
        for (N2HostOverview host : hosts) {
            if (host.getId().equalsIgnoreCase(ipaddress)) {
                return host;
            }
        }
        return null;
    }

    /**
     * Fetches a wrapper that holds overviews and details of all hosts.
     * @return N2OverviewDetailsWrapper object
     */
    public N2OverviewDetailsWrapper fetchHostOverviewsIncludingDetails() {
        return this.responseToObject(this.client.executeHttpGetUrl(DETAILS_URL), new TypeReference<N2OverviewDetailsWrapper>() {
        });
    }

    /**
     * Fetches a list of hostdetails between 2 dates.
     * @param ipaddress The ipaddress of the host.
     * @param startDate The first date you want to fetch.
     * @param endDate The last date you want to fetch.
     * @return List of N2HostDetail objects.
     */
    public List<N2HostDetail> fetchHostDetails(String ipaddress, DateTime startDate, DateTime endDate) {
        String path = ipaddress + STARTDATE_URL + startDate.toString(this.dateTimeFormatter)
                + ENDDATE_URL + endDate.toString(this.dateTimeFormatter);
        return this.responseToObject(this.client.executeHttpGetUrl(path), new TypeReference<List<N2HostDetail>>() {
        });
    }

    /**
     * Fetches detail averages between two dates.
     * @param ipaddress The ipaddress of the host.
     * @param startDate The first date you want to fetch.
     * @param endDate The last date you want to fetch.
     * @param average The type of average you want to get.
     * @return List of N2HostDetailAverage objecten.
     */
    public List<N2HostDetailAverage> fetchHostDetailAverages(String ipaddress, DateTime startDate, DateTime endDate,
            AverageType average) {
        String path = ipaddress + STARTDATE_URL + startDate.toString(this.dateTimeFormatter)
                + ENDDATE_URL + endDate.toString(this.dateTimeFormatter) + AVERAGE_URL + average.getName();
        return this.responseToObject(this.client.executeHttpGetUrl(path), new TypeReference<List<N2HostDetailAverage>>() {
        });
    }

    /**
     * Fetches detail information of a specific host at a given datetime.
     * @param ipaddress The ip address of the host you want to fetch data from.
     * @param date The date you want to fetch the data from.
     * @return N2HostDetail Object.
     */
    public N2HostDetail fetchHostDetail(String ipaddress, DateTime date) {
        String path = ipaddress + DATE_URL + date.toString(this.dateTimeFormatter);
        return this.responseToObject(this.client.executeHttpGetUrl(path), new TypeReference<N2HostDetail>() {
        });
    }

    /**
     * Fetches an eventlog of a specific host.
     * @param ipaddress The ip address of the host you want to get an eventlog from.
     * @return N2HostEventLog Object.
     */
    public N2HostEventLog fetchHostEventLog(String ipaddress) {
        String path = ipaddress + EVENTLOG_URL;
        return this.responseToObject(this.client.executeHttpGetUrl(path), new TypeReference<N2HostEventLog>() {
        });
    }

    /**
     * Fetches data used in the graphs of the n2viewer of a specific host and datetime.
     * @param ipaddress The ip address of the host you want to get data from.
     * @param graphType The type of graph you want data from.
     * @param date The date you want the data from, ALERT: keep in account that the data returned starts one day earlier.
     *                  Example: given = 2014-01-12T12:30 returned = 2014-01-12T12:29 and earlier.   
     * @param interval The interval at which the data is retrieved.
     *                  Example: N2Interval.FOUR_HOURS gives steps of 1 minute, while N2Interval.ONE_DAY gives steps of 4 minutes
     * @return N2GraphData Object.
     */
    public N2GraphData fetchGraphData(String ipaddress, N2GraphType graphType, DateTime date, N2Interval interval) {
        String path = ipaddress + GRAPH_URL + graphType + DATE_URL + date.toString(this.dateTimeFormatter)
                + INTERVAL_URL + interval;
        return this.responseToObject(this.client.executeHttpGetUrl(path), new TypeReference<N2GraphData>() {
        });
    }

    /**
     * Maps the content of a response to a Java Object.
     * @param response The response from a request.
     * @param type The class that you want the response to be mapped as.
     * @return Object of the type class.
     */
    private <T> T responseToObject(HttpResponse response, TypeReference<T> type) {
        try {
            LOGGER.debug("Trying to map json to " + type.getType());
            if (null != response)
                return objectMapper.readValue(response.getEntity().getContent(), type);
        } catch (JsonParseException e) {
            LOGGER.error("Json parse exception, message: " + e.getOriginalMessage());
        } catch (JsonMappingException e) {
            LOGGER.error("Json mapping exception, message: " + e.getOriginalMessage());
        } catch (IllegalStateException e) {
            LOGGER.error("Illegal state exception, message: " + e.getLocalizedMessage());
        } catch (IOException e) {
            LOGGER.error("IO exception, message: " + e.getLocalizedMessage());
        } finally {
            close(response);
            LOGGER.debug("Closed the response");
        }
        return null;
    }

    private void close(HttpResponse response) {
        try {
            if (null != response)
                EntityUtils.consume(response.getEntity());
        } catch (IOException e) {
            LOGGER.error("IO exception while closing httpresponse, message:" + e.getLocalizedMessage());
        }
    }

    public HttpClient getClient() {
        return client;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

}
