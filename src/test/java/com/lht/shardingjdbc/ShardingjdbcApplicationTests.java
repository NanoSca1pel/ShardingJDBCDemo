package com.lht.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lht.shardingjdbc.entity.Course;
import com.lht.shardingjdbc.entity.Dict;
import com.lht.shardingjdbc.entity.User;
import com.lht.shardingjdbc.mapper.CourseMapper;
import com.lht.shardingjdbc.mapper.DictMapper;
import com.lht.shardingjdbc.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingjdbcApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DictMapper dictMapper;


    //====================================测试水平分库分表 配置文件fenku、 fenbiao======================================
    @Test
    public void addCourse() {
        for(int i=1; i<=10; i++) {
            Course course = new Course();
            course.setName("数学"+i);
            course.setUserId(100L+i);
            course.setState(0);
            courseMapper.insert(course);
        }
    }

    @Test
    public void queryCourseById() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1295607860574208001L);
        Course course = courseMapper.selectOne(queryWrapper);
        System.out.println(course);
    }

    @Test
    public void queryCourseByUserId() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", 101L);
        Course course = courseMapper.selectOne(queryWrapper);
        System.out.println(course);
    }


    @Test
    public void queryAllCourses() {
        List<Course> course = courseMapper.selectList(null);
        course.forEach(System.out::println);
    }

    //====================================测试垂直分库 配置文件chuizhifenku; 测试读写分离 配置文件duxiefenli======================================
    @Test
    public void addUserDb() {
        User user = new User();
        user.setUsername("钱老板");
        user.setState(1);
        userMapper.insert(user);
    }

    @Test
    public void queryUserById() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1296340652127858690L);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    //====================================测试公共表 配置文件chuizhifenku======================================
    @Test
    public void addDictDb() {
        Dict dict = new Dict();
        dict.setState(1);
        dict.setValue("语文");
        dictMapper.insert(dict);
    }

    @Test
    public void deleteDictDb() {
        Dict dict = new Dict();
        dict.setState(1);
        dict.setValue("语文");
        dictMapper.deleteById(1298095468264976386L);
    }

    @Test
    public void queryDictById() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("id", 1296340652127858690L);
        Dict dict = dictMapper.selectOne(queryWrapper);
        System.out.println(dict);
    }
}
