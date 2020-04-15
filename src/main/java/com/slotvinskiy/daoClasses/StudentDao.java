package com.slotvinskiy.daoClasses;

import com.slotvinskiy.modelClasses.Group;
import com.slotvinskiy.modelClasses.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDao {

    private SessionFactory sessionFactory;

    public StudentDao() {
        sessionFactory = SessionFactoryConfigurator.getSessionFactoryWithConfigurations();
    }

    public void removeAllStudents() {
        System.out.println(clear() + " students were deleted");
    }

    private int clear() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", Student.class.getSimpleName());
            Query query = session.createQuery(hql);
            int count = query.executeUpdate();
            transaction.commit();
            return count;
        }
    }

    public void addStudent(Student student) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }

    }

    public Student getStudentBy(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Student WHERE studentId = :studentId", Student.class)
                    .setParameter("studentId", id)
                    .getSingleResult();
        }
    }

    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Student", Student.class)
                    .list();
        }
    }

    public void updateStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        }
    }

    public List<Student> getStudentsFromGroup(String groupName) {

        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT students from Group where lower(groupName) = lower(:groupName)")
                    .setParameter("groupName", groupName)
                    .list();
        }
    }

    public List<Group> getGroupsOfStudent(String studentName) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT groups from Student where lower(studentName) = lower(:studentName)")
                    .setParameter("studentName", studentName)
                    .list();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
