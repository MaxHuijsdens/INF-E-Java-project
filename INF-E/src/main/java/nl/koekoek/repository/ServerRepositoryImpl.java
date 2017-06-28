package nl.koekoek.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import nl.koekoek.model.QServer;
import nl.koekoek.model.Server;
import nl.koekoek.model.ServerIptable;
import nl.koekoek.model.ServerUser;
import nl.koekoek.model.ServerUserChange;
import nl.koekoek.model.ServerVersion;
import nl.koekoek.model.ServerVersionJson;

import com.mysema.query.jpa.impl.JPAQuery;
import nl.koekoek.model.ServerJson;

/**
 * Implementation of the custom serverRepository.
 * @author bastiaan
 *
 */
public class ServerRepositoryImpl implements ServerRepositoryCustom {

    private QServer qServer = QServer.server;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Gets all current versions for all servers.
     * @return list with all current versions for all servers
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ServerVersionJson> getServerVersionsList(Long slaId) {
        JPAQuery query = new JPAQuery(entityManager);
        List<Server> results = null;
        if (slaId != null) {
            results = query.from(qServer).where(qServer.sla.id.eq(slaId)).list(qServer);
        } else {
            results = query.from(qServer).list(qServer);
        }
        List<ServerVersionJson> serverVersions = new ArrayList<ServerVersionJson>();
        for (Server s : results) {
            ServerVersionJson serverVersionJson = new ServerVersionJson();
            serverVersionJson.setServerId(s.getId());
            Query q = entityManager.createNativeQuery("SELECT v.name, v.version_number FROM version v INNER JOIN server_version sv "
                    + "ON sv.version_id = v.id WHERE sv.version_id IN (SELECT MAX(svt.version_id) "
                    + "FROM server_version svt INNER JOIN version vt ON svt.version_id = vt.id "
                    + "GROUP BY vt.name) AND sv.server_id = :serverId").setParameter("serverId", s.getId());
            //TODO ipb object versies ophalen maar kon niet casten van object naar version...?
            List<Object[]> versions = (List<Object[]>) q.getResultList();
            Map<String, String> jsonFormatVersions = new HashMap<String, String>();
            for (Object[] o : versions) {
                jsonFormatVersions.put(o[0].toString(), o[1].toString());
            }
            serverVersionJson.setVersions(jsonFormatVersions);
            serverVersionJson.setHostName(s.getHostName());
            serverVersionJson.setSupportName(s.getSupportName());
            serverVersions.add(serverVersionJson);
        }
        return serverVersions;
    }

    /**
     * @param serverId the server id where you want all versions from.
     * @return a serverVersion object
     */
    @Override
    public List<ServerVersion> getLatestServerVersions(long serverId) {
        TypedQuery<ServerVersion> q = entityManager.createQuery("SELECT sv FROM ServerVersion sv  WHERE sv.version.id IN (SELECT MAX(svt.version.id) " +
                "FROM ServerVersion svt INNER JOIN svt.version vt " +
                "GROUP BY vt.name) AND sv.server.id = :serverId", ServerVersion.class).setParameter("serverId", serverId);
        return q.getResultList();
    }

    @Override
    public List<ServerUser> getAllNotKnownServerUsers(long serverId) {
        return entityManager
                .createQuery("SELECT su FROM ServerUser su INNER JOIN su.user u WHERE u.known = FALSE AND su.server.id = :serverId", ServerUser.class)
                .setParameter("serverId", serverId).getResultList();
    }

    @Override
    public List<ServerUserChange> getAllNotKnownServerUserChange(long serverId) {
        return entityManager.createQuery("SELECT suc FROM ServerUserChange suc INNER JOIN suc.user u WHERE u.known = FALSE AND suc.server.id = :serverId",
                ServerUserChange.class).setParameter("serverId", serverId).getResultList();
    }

    @Override
    public List<ServerIptable> getAllNotKnownServerIptable(long serverId) {
        return entityManager.createQuery("SELECT si FROM ServerIptable si INNER JOIN si.iptable i WHERE i.known = FALSE AND si.server.id = :serverId",
                ServerIptable.class).setParameter("serverId", serverId).getResultList();
    }

    @Override
    public List<ServerJson> getAllServers() {
	
	JPAQuery query = new JPAQuery(entityManager);
        List<Server> results = null;
        results = query.from(qServer).list(qServer);
	
        List<ServerJson> servers = new ArrayList<>();
        for (Server s : results) {
            ServerJson serverJson = new ServerJson();
            serverJson.setServer(s);
            
	    try
	    {
		Query q = entityManager.createNativeQuery("SELECT DATE_PART('day', NOW() - MAX(incident_date)) as last_check "
		    + "FROM note " 
		    + "WHERE server_id = :serverId");
		q.setParameter("serverId", s.getId());
		if(q.getSingleResult() != null)
		{
		    String lastChecked = String.valueOf(((Double)q.getSingleResult()).intValue());
		    serverJson.setLastChecked(lastChecked + " dagen");
		}
		else
		{
		    serverJson.setLastChecked("Geen notities");
		}
	    }
	    catch(Exception e)
	    {
		serverJson.setLastChecked("An error occurred: " + e);
	    }
            
	    servers.add(serverJson);
        }
	
        return servers;
    }

}
