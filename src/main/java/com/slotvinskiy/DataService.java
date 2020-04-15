package com.slotvinskiy;

import com.slotvinskiy.daoClasses.GroupDao;
import com.slotvinskiy.daoClasses.StudentDao;
import com.slotvinskiy.modelClasses.Group;
import com.slotvinskiy.modelClasses.Student;

public class DataService {

    private StudentDao studentDao = new StudentDao();
    private GroupDao groupDao = new GroupDao();

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public void addGroup(Group group) {
        groupDao.addGroup(group);
    }

    public Group getGroupById(int id) {
        return groupDao.getGroup(id);
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void close() {
        studentDao.close();
        groupDao.close();
    }
}
