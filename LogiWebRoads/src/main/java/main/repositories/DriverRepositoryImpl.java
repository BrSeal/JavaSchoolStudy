package main.repositories;

import main.model.users.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverRepositoryImpl implements DriverRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public DriverRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Driver> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Driver ", Driver.class)
                .list();
    }

    public Driver get(int id) {
        return sessionFactory.getCurrentSession()
                .get(Driver.class, id);
    }

    @Override
    public int save(Driver driver) {
        return (int)sessionFactory.getCurrentSession()
                .save(driver);
    }

    @Override
    public Driver delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Driver driver = session.get(Driver.class, id);
        session.delete(driver);
        return driver;
    }

    public Driver delete(Driver driver) {
        sessionFactory.getCurrentSession().delete(driver);
        return driver;
    }

    @Override
    public void update(Driver driver) {
        sessionFactory.getCurrentSession()
                .update(driver);
    }
}
