package com.slotvinskiy.modelClasses;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {


    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private int studentId;

    @Column
    private String studentName;

    private int age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "students_groups",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")}
    )
    private Set<Group> groups = new HashSet<>();

    public Student() {
    }

    public Student(String name, int age) {
        this.studentName = name;
        this.age = age;
    }

    public Student(int studentId, String studentName, int age, Set<Group> groups) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.groups = groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
        group.getStudents().add(this);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "{" +
                "studentId=" + studentId +
                ", name='" + studentName + '\'' +
                ", age=" + age +
                '}';
    }
}
