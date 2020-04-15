package com.slotvinskiy.modelClasses;


import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Proxy(lazy = false)
public class Group {

    @Id
    @Column(name = "group_id")
    private int groupId;

    @Column
    private String groupName;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    public Group() {
    }

    public Group(int groupId, String name) {
        this.groupId = groupId;
        this.groupName = name;
    }

    public Group(int groupId, String groupName, Set<Student> students) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.students = students;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return groupName;
    }

    public void setName(String name) {
        this.groupName = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "{" +
                "groupId=" + groupId +
                ", name='" + groupName + '\'' +
                '}';
    }

}
