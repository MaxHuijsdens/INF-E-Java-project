package nl.koekoek.controller.model;

import nl.koekoek.model.Server;
import nl.koekoek.model.Sla;
import nl.koekoek.model.Supplier;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the http requestbody data of the server create/edit form.
 * @author Kevin van Leeuwen
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerJsonModel implements JsonModel<Server> {

    private String supportName;
    private String hostName;
    private String ipv4;
    private String ipv6;
    private String image;
    private String hostDns;
    private String otap;
    private double cost;
    private int totalRam;
    private int totalDiskSize;
    private int cores;
    private String confluencePageUrl;
    private boolean monitoredByServercheck;
    private boolean inHybernation;
    private String purpose;
    private boolean monitoredByNewrelic;
    private String remarks;
    private Sla sla;
    private Supplier supplier;

    @Override
    public Server buildNew() {
        Server server = new Server();
        server.setSupportName(this.supportName);
        server.setHostName(this.hostName);
        server.setIpv4(this.ipv4);
        server.setIpv6(this.ipv6);
        server.setImage(this.image);
        server.setHostDns(this.hostDns);
        server.setOtap(this.otap);
        server.setCost(this.cost);
        server.setTotalRam(this.totalRam);
        server.setTotalDiskSize(this.totalDiskSize);
        server.setCores(this.cores);
        server.setConfluencePageUrl(this.confluencePageUrl);
        server.setMonitoredByServercheck(this.monitoredByServercheck);
        server.setInHybernation(this.inHybernation);
        server.setPurpose(this.purpose);
        server.setMonitoredByNewrelic(this.monitoredByNewrelic);
        server.setRemarks(this.remarks);
        server.setSupplier(this.supplier);
        server.setSla(this.sla);
        return server;
    }

    @Override
    public Server mergeInto(Server server) {
        server.setSupportName(this.supportName);
        server.setHostName(this.hostName);
        server.setIpv4(this.ipv4);
        server.setIpv6(this.ipv6);
        server.setImage(this.image);
        server.setHostDns(this.hostDns);
        server.setOtap(this.otap);
        server.setCost(this.cost);
        server.setTotalRam(this.totalRam);
        server.setTotalDiskSize(this.totalDiskSize);
        server.setCores(this.cores);
        server.setConfluencePageUrl(this.confluencePageUrl);
        server.setMonitoredByServercheck(this.monitoredByServercheck);
        server.setInHybernation(this.inHybernation);
        server.setPurpose(this.purpose);
        server.setMonitoredByNewrelic(this.monitoredByNewrelic);
        server.setRemarks(this.remarks);
        server.setSupplier(this.supplier);
        server.setSla(this.sla);
        return server;
    }
}
