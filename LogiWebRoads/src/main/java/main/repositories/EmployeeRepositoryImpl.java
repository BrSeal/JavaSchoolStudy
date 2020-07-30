package main.repositories;

import main.model.users.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Employee> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employee ", Employee.class)
                .list();
    }

    public Employee get(int id) {
        return sessionFactory.getCurrentSession()
                .get(Employee.class, id);
    }

    @Override
    public int save(Employee e) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(e);
        return e.getId();
    }

    @Override
    public Employee delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee e = session.get(Employee.class, id);
        session.delete(e);
        return e;
    }

    @Override
    public Employee delete(Employee employee) {
        sessionFactory.getCurrentSession().delete(employee);
        return employee;
    }
}
