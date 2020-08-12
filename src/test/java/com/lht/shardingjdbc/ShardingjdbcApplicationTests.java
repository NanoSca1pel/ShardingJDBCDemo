package com.lht.shardingjdbc;

import com.lht.shardingjdbc.entity.Course;
import com.lht.shardingjdbc.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingjdbcApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void addCourse() {
        Course course = new Course();
        course.setName("数学");
        course.setUserId(100L);
        course.setState(0);
        courseMapper.insert(course);
    }

}
