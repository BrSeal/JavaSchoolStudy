package main.core.vehicle;

import main.core.vehicle.entity.Vehicle;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public VehicleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Vehicle> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Vehicle ", Vehicle.class)
                .list();
    }

    @Override
    public Vehicle get(int id) {
        return sessionFactory.getCurrentSession()
                .get(Vehicle.class, id);
    }

    @Override
    public int save(Vehicle vehicle) {
        return (int) sessionFactory.getCurrentSession()
                .save(vehicle);
    }

    @Override
    public Vehicle delete(Vehicle vehicle) {
        sessionFactory.getCurrentSession().delete(vehicle);
        return vehicle;
    }

    @Override
    public List<Vehicle> getByQuery(String hql, Map<String,Object> params) {
        Query<Vehicle> query= sessionFactory
                .getCurrentSession()
                .createQuery(hql, Vehicle.class);
        if(params!=null) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }
        return query.list();
    }

    @Override
    public void update(Vehicle vehicle) {
        sessionFactory.getCurrentSession().update(vehicle);
    }
}
