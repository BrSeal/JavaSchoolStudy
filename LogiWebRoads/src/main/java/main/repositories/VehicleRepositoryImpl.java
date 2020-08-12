package main.repositories;

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
       return (int) sessionFactory.getCurrentSession()
                .save(vehicle);
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

    @Override
    public void update(Vehicle vehicle) {
        sessionFactory.getCurrentSession().update(vehicle);
    }
}
