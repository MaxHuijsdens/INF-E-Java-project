/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.koekoek.model;

/**
 *
 * @author Robin
 */
public class ServerJson {

    private Server server;
    private String lastChecked;
    
    public Server getServer() { return server; }
    public String getLastChecked() { return lastChecked; }
    
    public void setServer(Server server) { this.server = server; }
    public void setLastChecked(String lastChecked) { this.lastChecked = lastChecked; }
}
