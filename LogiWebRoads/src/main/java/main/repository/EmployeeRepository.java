package main.repository;

import main.model.users.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Employee> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employee ", Employee.class)
                .getResultList();
    }

//
//    public Employee getById(int id){
//        query=session.createQuery("from Employee where id={id}");
//    }
//
//    public int save(Employee employee){
//
//    }
//
//    public int deleteById(int id){
//
//    }
//
//    public int editById(int id) {
//
//    }
}
