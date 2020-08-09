package main.repositories;

import main.model.logistic.Order;
import main.model.users.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


//TODO Сделать адекватное добавление заказов пока что без логики
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Order ", Order.class)
                .list();
    }

    @Override
    public Order get(int id) {
        return sessionFactory.getCurrentSession().get(Order.class, id);
    }

    @Override
    public int save(Order order) {
       return (int) sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public Order delete(int id) {
        Session s = sessionFactory.getCurrentSession();
        Order order = s.get(Order.class, id);
        s.delete(order);
        return order;
    }

    @Override
    public Order delete(Order order) {
        sessionFactory.getCurrentSession().delete(order);
        return order;
    }

    @Override
    public void update(Order order) {
        sessionFactory.getCurrentSession()
                .update(order);
    }
}
