package main.core.cityAndRoads.roads;

import main.core.cityAndRoads.roads.entity.Road;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoadRepositoryImpl implements RoadRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoadRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public int add(Road road) {
        return (int)sessionFactory.getCurrentSession().save(road);
    }

    @Override
    public void addAll(List<Road> roads) {
        for (Road road : roads) {
            add(road);
        }
    }

    public Road get(int id) {
        return sessionFactory.getCurrentSession()
                .get(Road.class, id);
    }

    public List<Road> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Road ", Road.class)
                .list();
    }

    @Override
    public void update(Road road) {
        sessionFactory.getCurrentSession().update(road);
    }

    @Override
    public void delete(Road road) {
            sessionFactory.getCurrentSession().delete(road);
    }


}
