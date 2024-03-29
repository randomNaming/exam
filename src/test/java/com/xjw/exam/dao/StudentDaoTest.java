package com.xjw.exam.dao;

import com.xjw.exam.entity.Student;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    @Ignore
    public void queryStudent() {
        List<Student> studentList = studentDao.findAllList();
        assertEquals(2,studentList.size());
    }

    @Test
    public void queryStudentById() {
        Student student = studentDao.get("222");
        assertEquals("admin", student.getName());
    }

    @Test
    public void insertStudent() {
        Student student = new Student();
        student.setId("121");
        student.setName("test1");
        student.setPassword("123341");
        int result = studentDao.insert(student);
        assertEquals(1,result);
    }

    @Test
    public void updateStudent() {
        Student student = new Student();
        student.setId("121");
        student.setName("guest");
        student.setPassword("123341");
        int result = studentDao.update(student);
        assertEquals(1,result);
    }

    @Test
    @Ignore
    public void deleteStudnet() {
        int result = studentDao.delete("121");
        assertEquals(1,result);
    }
}