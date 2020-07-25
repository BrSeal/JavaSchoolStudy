package main.repositories;

import main.model.users.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Employee> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("from Employee ", Employee.class)
                .getResultList();
    }

    public Employee getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("from Employee where id=" + id, Employee.class);
        return query.getResultList().get(0);
    }

    @Override
    public int save(Employee e) {
        return (int) sessionFactory.getCurrentSession().save(e);
    }

    @Override
    public boolean edit(int id, Employee changes) {
        return false;
    }

    @Override
    public Employee deleteById(int id) {
        return null;
    }
}
