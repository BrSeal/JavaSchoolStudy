package main.core.cityAndRoads.cities;

import main.model.logistic.City;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepositoryImpl implements CityRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public CityRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<City> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from City ", City.class)
                .list();
    }

    public City get(int id) {
        return sessionFactory.getCurrentSession()
                .get(City.class, id);
    }
}
