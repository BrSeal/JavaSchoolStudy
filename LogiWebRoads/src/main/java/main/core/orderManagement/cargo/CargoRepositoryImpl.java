package main.core.orderManagement.cargo;

import main.core.orderManagement.cargo.entity.Cargo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CargoRepositoryImpl implements CargoRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public CargoRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Cargo> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Cargo ", Cargo.class)
                .list();
    }

    @Override
    public Cargo get(int id) {
        return sessionFactory.getCurrentSession()
                .get(Cargo.class, id);
    }

    @Override
    public void update(Cargo Cargo) {
        sessionFactory.getCurrentSession()
                .update(Cargo);
    }
}
