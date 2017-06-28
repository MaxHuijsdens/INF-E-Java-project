package nl.koekoek.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.koekoek.model.Measurement;
import nl.koekoek.model.QServer;
import nl.koekoek.model.Server;

import org.joda.time.LocalDateTime;

import com.mysema.query.jpa.impl.JPAQuery;

public class MeasurementRepositoryImpl implements MeasurementRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;
    private QServer qServer = QServer.server;

    @SuppressWarnings("unchecked")
    @Override
    public List<Measurement> getLatestMeasurementsPerServer(Long slaId) {
        JPAQuery query = new JPAQuery(entityManager);
        List<Server> results = null;
        if (slaId != null) {
            results = query.from(qServer).where(qServer.sla.id.eq(slaId)).list(qServer);
        } else {
            results = query.from(qServer).list(qServer);
        }

        List<Measurement> serverMeasurements = new ArrayList<Measurement>();
        for (Server s : results) {
            Query q = entityManager.createQuery("select m1 from Measurement m1 where m1.timeOfMeasurement IN "
                    + "(SELECT max(m2.timeOfMeasurement) from Measurement m2 where m2.server.id = :serverId) "
                    + "and m1.server.id = :serverId order by m1.id desc").setParameter("serverId", s.getId());
            Measurement measurement = new Measurement();
            if (!q.getResultList().isEmpty()) {
                List<Measurement> measurements = (List<Measurement>) q.getResultList();
                measurement = measurements.get(0);
            } else {
                measurement.setServer(s);
            }
            serverMeasurements.add(measurement);

        }
        return serverMeasurements;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Number[]> findGraphicalDataByServerId(long id, String graphType, LocalDateTime startDate, LocalDateTime endDate, String source) {
        Query q = entityManager.createQuery("SELECT m1.timeOfMeasurement, m1." + graphType + " FROM Measurement m1 WHERE m1.timeOfMeasurement BETWEEN "
                + " :startDate AND :endDate AND m1.dataSource = :source AND m1.server.id = :serverId "
                + "ORDER BY m1.timeOfMeasurement ASC").setParameter("startDate", startDate)
                .setParameter("endDate", endDate).setParameter("source", source).setParameter("serverId", id);
        List<Object[]> result = (List<Object[]>) q.getResultList();
        for (Object[] object : result) {
            LocalDateTime dateTime = (LocalDateTime) object[0];
            object[0] = dateTime.toDateTime().getMillis();
        }
        return (List<Number[]>) (List<?>) result;
    }

}
