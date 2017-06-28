package nl.koekoek.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.koekoek.model.QServer;
import nl.koekoek.model.QServerVersion;
import nl.koekoek.model.QVersion;
import nl.koekoek.model.Version;

import com.mysema.query.jpa.impl.JPAQuery;

public class VersionRepositoryImpl implements VersionRepositoryCustom {

    private QServerVersion qSeverVersion = QServerVersion.serverVersion;
    private QServer qServer = QServer.server;
    private QVersion qVersion = QVersion.version;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Version findCurrentVersionByName(String versionName, String hostName) {
        JPAQuery query = new JPAQuery(entityManager);

        Version result = query.from(qVersion)
                .join(qVersion.serverVersions, qSeverVersion)
                .join(qSeverVersion.server, qServer)
                .where(qServer.hostName.eq(hostName)).where(qVersion.name.eq(versionName))
                .orderBy(qSeverVersion.id.desc())
                .singleResult(qVersion);
        return result;
    }
}
