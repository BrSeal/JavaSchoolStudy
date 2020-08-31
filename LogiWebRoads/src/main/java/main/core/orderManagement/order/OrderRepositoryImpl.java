package main.core.orderManagement.order;

import main.core.orderManagement.order.entity.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private static final String GET_ALL_ORDERS_HQL = "from Order o order by o.status";
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery(GET_ALL_ORDERS_HQL, Order.class)
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
    public void delete(Order order) {
        sessionFactory.getCurrentSession().delete(order);
    }

    @Override
    public void update(Order order) {
        sessionFactory.getCurrentSession()
                .update(order);
    }


}
