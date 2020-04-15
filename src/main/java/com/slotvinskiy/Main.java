package com.slotvinskiy;

import com.slotvinskiy.modelClasses.Group;
import com.slotvinskiy.modelClasses.Student;

import java.util.List;
import java.util.Random;

public class Main {

    private static DataService dataService;

    public static void main(String[] args) {

        System.out.println("++++++++++++++Program start+++++++++++++++++++");
        dataService = new DataService();
        clearAllGroups();
        clearAllStudents();
        addGroups();
        addStudents();

        setRandomGroupForAllStudents();
        show();
        dataService.close();
    }


    private static void setRandomGroupForAllStudents() {
        List<Student> students = dataService.getStudentDao().getAllStudents();
        List<Group> groups = dataService.getGroupDao().getAllGroups();

        for (Student s : students) {
            Student student = dataService.getStudentDao().getStudentBy(s.getStudentId());
            Random random = new Random();
            int randomId = random.nextInt(groups.size()) + 1;
            Group group = dataService.getGroupById(randomId);
            Group group2;
            if (randomId == 3) {
                group2 = dataService.getGroupById(randomId - 2);
            } else {
                group2 = dataService.getGroupById(randomId + 1);
            }
            student.addGroup(group);
            student.addGroup(group2);
            dataService.getStudentDao().updateStudent(student);
            dataService.getGroupDao().updateGroup(group);
        }
    }

    private static void addStudents() {

        dataService.addStudent(new Student("Lisa", 34));
        dataService.addStudent(new Student("Ben", 24));
        dataService.addStudent(new Student("Rob", 45));
        dataService.addStudent(new Student("Nik", 23));
        dataService.addStudent(new Student("Ash", 35));
        dataService.addStudent(new Student("Mike", 54));
        dataService.addStudent(new Student("Andy", 52));
    }

    private static void addGroups() {
        dataService.addGroup(new Group(1, "Java"));
        dataService.addGroup(new Group(2, "QA manual"));
        dataService.addGroup(new Group(3, "QA automation"));
    }

    private static void clearAllStudents() {
        dataService.getStudentDao().removeAllStudents();
    }

    private static void clearAllGroups() {
        dataService.getGroupDao().removeAllGroups();
    }

    private static void show() {

        String studentName = "Nik";
        System.out.printf("Student with \"%s\" has next list of groups:\n", studentName);
        List<Group> groups = dataService.getStudentDao().getGroupsOfStudent(studentName);
        System.out.println(groups);
        System.out.println();

        String groupName = "Java";
        System.out.printf("In group \"%s\" study next students:\n", groupName);
        List<Student> students = dataService.getStudentDao().getStudentsFromGroup(groupName);
        System.out.println(students);
        System.out.println();
    }
}
