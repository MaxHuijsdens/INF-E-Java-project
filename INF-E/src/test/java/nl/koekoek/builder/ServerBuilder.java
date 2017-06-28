/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.builder;

import nl.koekoek.model.Server;
import nl.koekoek.model.Sla;
import nl.koekoek.service.ServerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerBuilder {
    
    @Autowired
    private ServerService serverService;
    
    public ServerBuildCommand newServer() {
        return new ServerBuildCommand();
    }

    public class ServerBuildCommand {

        private Server server = new Server();
        
        public ServerBuildCommand withHostName(String hostName) {
            server.setHostName(hostName);
            return this;
        }
        
        public ServerBuildCommand withSla(Sla sla) {
            server.setSla(sla);
            return this;
        }

        public Server create() {
            serverService.addServer(server);
            return server;
        }

    }
    
}
