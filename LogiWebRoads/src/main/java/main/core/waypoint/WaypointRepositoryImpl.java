package main.core.waypoint;

import main.model.logistic.Cargo;
import main.model.logistic.Waypoint;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
       return (int) sessionFactory.getCurrentSession().save(waypoint);

    }

    @Override
    public void update(Waypoint waypoint) {
        sessionFactory.getCurrentSession().update(waypoint);
    }

    @Override
    public List<Waypoint> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Waypoint ", Waypoint.class)
                .list();
    }

    @Override
    public List<Waypoint> getByCargo(Cargo cargo) {
        String hql="from Waypoint w where w.cargo=:cargo";
       Query<Waypoint> query= sessionFactory.getCurrentSession().createQuery(hql,Waypoint.class);
        query.setParameter("cargo", cargo);
        return query.list();
    }

    @Override
    public Waypoint get(int id) {
        return sessionFactory.getCurrentSession().get(Waypoint.class, id);
    }

    @Override
    public Waypoint delete(Waypoint waypoint) {
        sessionFactory.getCurrentSession().delete(waypoint);
        return waypoint;
    }
}
