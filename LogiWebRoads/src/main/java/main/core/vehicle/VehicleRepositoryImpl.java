package main.core.vehicle;

import main.model.logistic.Vehicle;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Vehicle> getByOrderId(int orderId) {
        String query = "from Vehicle v where v.currentOrder=" + orderId;
        return sessionFactory
                .getCurrentSession()
                .createQuery(query, Vehicle.class)
                .list();

    }

    @Override
    public void update(Vehicle vehicle) {
        sessionFactory.getCurrentSession().update(vehicle);
    }
}
