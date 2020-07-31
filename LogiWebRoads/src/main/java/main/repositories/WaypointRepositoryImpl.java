package main.repositories;

import main.model.logistic.Waypoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class WaypointRepositoryImpl implements WaypointRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public WaypointRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int save(Waypoint waypoint) {
        sessionFactory.getCurrentSession().saveOrUpdate(waypoint);
        return waypoint.getId();
    }

    @Override
    public void saveAll(List<Waypoint> waypoints) {
        waypoints.forEach(sessionFactory.getCurrentSession()::save);
    }

    @Override
    public List<Waypoint> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Waypoint ", Waypoint.class)
                .list();
    }

    @Override
    public Waypoint get(int id) {
        return sessionFactory.getCurrentSession().get(Waypoint.class, id);
    }

    @Override
    public Waypoint delete(int id) {
        Session s = sessionFactory.getCurrentSession();
        Waypoint deleted = s.get(Waypoint.class, id);
        s.delete(deleted);
        return deleted;
    }

    @Override
    public Waypoint delete(Waypoint waypoint) {
        sessionFactory.getCurrentSession().delete(waypoint);
        return waypoint;
    }
}
