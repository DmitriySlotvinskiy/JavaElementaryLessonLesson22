package com.slotvinskiy.daoClasses;

import com.slotvinskiy.modelClasses.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class GroupDao {

    private SessionFactory sessionFactory;

    public GroupDao() {
        sessionFactory = SessionFactoryConfigurator.getSessionFactoryWithConfigurations();
    }

    public void removeAllGroups() {
        System.out.println(clear() + " groups were deleted");
    }

    private int clear() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", Group.class.getSimpleName());
            Query query = session.createQuery(hql);
            int count = query.executeUpdate();
            transaction.commit();
            return count;
        }
    }

    public void addGroup(Group group) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
        }

    }

    public Group getGroup(int groupId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Group WHERE group_id = :id", Group.class)
                    .setParameter("id", groupId)
                    .getSingleResult();
        } catch (NoResultException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public void updateGroup(Group group) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(group);
            transaction.commit();
        }
    }

    public List<Group> getAllGroups() {

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Group", Group.class).list();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
