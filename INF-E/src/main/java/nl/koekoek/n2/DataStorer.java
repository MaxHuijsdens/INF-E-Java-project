package nl.koekoek.n2;

import java.util.List;

import nl.koekoek.model.N2Measurement;
import nl.koekoek.model.Server;
import nl.koekoek.n2.api.AccountConfig;
import nl.koekoek.n2.api.HttpClient;
import nl.koekoek.n2.api.N2DataFetcher;
import nl.koekoek.n2.model.N2HostDetail;
import nl.koekoek.n2.model.N2HostOverview;
import nl.koekoek.n2.model.N2MountedFileSystem;
import nl.koekoek.repository.ServerRepository;
import nl.koekoek.service.MeasurementService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Class for converting and storing the latest n2 measurements.
 * @author Tom
 * 
 */
public class DataStorer {

    private AccountConfig accountConfig;
    private HttpClient httpClient;
    private N2DataFetcher dataFetcher;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private MeasurementService measurementService;

    /**
     * Default constructor.
     */
    public DataStorer() {

    }

    /**
     * Constructor for DataStorer.
     * @param accountConfig 
     */
    public DataStorer(AccountConfig accountConfig) {
        this.accountConfig = accountConfig;
        this.httpClient = new HttpClient(this.accountConfig);
        this.dataFetcher = new N2DataFetcher(this.httpClient);
    }

    /**
     * Retrieve and store the latest measurements. To be safe we collect the data at the end of the minute(50 seconds).
     */
    @Async
    @Scheduled(cron = "50 * * * * ?")
    public void retrieveAndStore() {
        if (this.accountConfig.isN2IsUsed()) {
            System.out.println("kom hier wel");
            DateTime dateTime = new DateTime();
            dateTime = new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(),
                    dateTime.getHourOfDay(), dateTime.getMinuteOfHour(), 0, 0);
            //retrieve all hosts from n2
            List<N2HostOverview> hosts = dataFetcher.fetchAllHosts();
            if (null != hosts) {

                for (N2HostOverview host : hosts) {
                    //try to get the server
                    Server server = serverRepository.findByHostName(host.getLabel());
                    //if the server was not found create a new server
                    if (null == server) {
                        server = new Server();
                        server.setIpv4(host.getId());
                        server.setHostName(host.getLabel());
                        serverRepository.save(server);
                    }
                    if (server.getIpv4() == null || server.getIpv4().equals("")) {
                        server.setIpv4(host.getId());
                        serverRepository.save(server);
                    }
                    N2HostDetail hostDetail = dataFetcher.fetchHostDetail(host.getId(), dateTime);
                    if (null != hostDetail) {

                        List<N2MountedFileSystem> disks = hostDetail.getMountedFilesystems();
                        double diskUsage = 0;
                        if (null != disks) {
                            for (N2MountedFileSystem fileSystem : disks) {
                                diskUsage += fileSystem.getUsage();
                            }
                            diskUsage = diskUsage / disks.size();
                        }

                        //create new n2measurement 
                        N2Measurement n2measurement = new N2Measurement(server, dateTime.toLocalDateTime(), host.getStatus(), hostDetail.getUptimeString(),
                                hostDetail.getLoadAverage(), hostDetail.getCpuUsage(), (diskUsage), hostDetail.getNetworkIn(),
                                hostDetail.getNetworkOut(), hostDetail.getDiskActivity(), hostDetail.getRoundTripTime(), hostDetail.getIoWaitPercent(),
                                hostDetail.getFreeRAM(), hostDetail.getFreeSwapMemory(), hostDetail.getNumberOfRunningProcesses());
                        measurementService.saveN2Measurement(n2measurement);
                    }
                }
            }
        }
    }

}
