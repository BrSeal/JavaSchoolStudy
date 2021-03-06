package main.core.driver;

import main.core.driver.entity.Driver;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class DriverRepositoryImpl implements DriverRepository {
    private static final String DRIVER_BY_ORDER_HQL = "from Driver d where d.currentOrder=";


    private final SessionFactory sessionFactory;

    @Autowired
    public DriverRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Driver> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Driver ", Driver.class)
                .list();
    }

    @Override
    public List<Driver> getByQuery(String hql, Map<String,Object> params) {
        Query<Driver> query = sessionFactory.getCurrentSession()
                .createQuery(hql, Driver.class);

        if(params!=null) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }
        return query.list();
    }

    @Override
    public List<Driver> getByOrderId(int orderId) {
        String hql = DRIVER_BY_ORDER_HQL+orderId;
        return sessionFactory.getCurrentSession().createQuery(hql, Driver.class).list();
    }

    @Override
    public Driver get(int id) {
        return sessionFactory.getCurrentSession()
                .get(Driver.class, id);
    }

    @Override
    public int save( Driver driver) {
        return (int) sessionFactory.getCurrentSession()
                .save(driver);
    }

    @Override
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
