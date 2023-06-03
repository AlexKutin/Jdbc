package dao;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtils;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public Employee findById(Integer id) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> findAll() {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery("SELECT * FROM Employee", Employee.class).list();
        }
    }

    @Override
    public void create(Employee employee) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    @Override
    public void update(Employee employee) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public int deleteById(Integer id) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int countRows = session.createNativeQuery(
                    "DELETE FROM EMPLOYEE WHERE id = :id", Employee.class)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
            return countRows;
        }
    }

    @Override
    public void delete(Employee employee) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtils.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}
