package dao;

import model.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtils;

public class CityDAOImpl implements CityDAO {

    @Override
    public City findById(Integer cityId) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            return session.find(City.class, cityId);
        }
    }

    @Override
    public void create(City city) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public Integer createByName(String cityName) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            City city = new City();
            city.setCityName(cityName);
            Integer cityId = (Integer) session.save(city);
            transaction.commit();
            return cityId;
        }
    }

    @Override
    public void update(City city) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void delete(City city) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}
