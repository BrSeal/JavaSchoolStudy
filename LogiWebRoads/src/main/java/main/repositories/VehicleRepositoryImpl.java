package main.repositories;

import main.model.logistic.Vehicle;
import org.hibernate.Session;
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


    public List<Vehicle> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Vehicle ", Vehicle.class)
                .list();
    }

    public Vehicle get(int id) {
        return sessionFactory.getCurrentSession()
                .get(Vehicle.class, id);
    }

    @Override
    public int save(Vehicle vehicle) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(vehicle);
        return vehicle.getId();
    }

    @Override
    public Vehicle delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Vehicle e = session.get(Vehicle.class, id);
        session.delete(e);
        return e;
    }

    @Override
    public Vehicle delete(Vehicle vehicle) {
        sessionFactory.getCurrentSession().delete(vehicle);
        return vehicle;
    }


    //TODO сделать поиск подходящих под заказ авто
    @Override
    public List<Vehicle> getAvailable() {

       return sessionFactory
               .getCurrentSession()
               .createQuery("from Vehicle where Vehicle.isOk",Vehicle.class)
               .list();

    }
}
