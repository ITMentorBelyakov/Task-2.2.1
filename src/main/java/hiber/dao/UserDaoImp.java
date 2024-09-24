package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public User getUserFromCar(String model, String series) {
        String hql = "FROM User WHERE userCar.model = :model AND userCar.series = :series";

        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series" ,series);

        List<User> users = query.list();
        return users.isEmpty() ? null : users.get(0);
    }
}
