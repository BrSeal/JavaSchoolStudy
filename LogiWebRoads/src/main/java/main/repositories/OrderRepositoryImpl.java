package main.repositories;

import main.model.logistic.Order;
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
        return null;
    }

    @Override
    public int save(Order order) {
        return 0;
    }

    @Override
    public Order delete(int id) {
        return null;
    }

    @Override
    public Order delete(Order order) {
        return null;
    }
}
