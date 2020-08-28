package com.lht.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lht.shardingjdbc.entity.*;
import com.lht.shardingjdbc.mapper.CourseMapper;
import com.lht.shardingjdbc.mapper.DictMapper;
import com.lht.shardingjdbc.mapper.UserMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
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


    //lambda()下只能用User::get属性构造，非lambda模式可以使用i->操作
    @Test
    public void test1() {
        /*QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","username").eq("username", "钱老板").apply("id=1296340652127858691");*/
        /*User u = new User();
        queryWrapper.setEntity(u);
        queryWrapper.select(i -> i.getProperty().startsWith("id")).eq("username", "钱老板");*/
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQueryWrapper = queryWrapper.lambda().select(User::getId,User::getUsername).eq(User::getUsername, "钱老板");
        User user = userMapper.selectOne(lambdaQueryWrapper);
        System.out.println(user);
    }

    @Test
    public void test2() {
        User u = new User();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("state = 2").apply("id=1296340652127858690");
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = updateWrapper.lambda().eq(User::getId, "1298159727963455489").set(User::getUsername, "李老板");
        int update = userMapper.update(u,lambdaUpdateWrapper);
        System.out.println(update);
    }

    @Test
    public void test3() {
        User user = new User();
        user.setUsername("徐老板");
        user.setState(1);
        boolean insert = user.insert();
        System.out.println(insert);

        user.setUsername("蔡徐坤");
        boolean update = user.updateById();
        System.out.println(update);

        User result = user.selectById();
        System.out.println(result.toString());


        boolean delete = user.deleteById(1L);
        System.out.println(delete);
    }

    @Test
    public void test4() {
        UserPage userPage = new UserPage();
        userPage.setCurrent(1);
        userPage.setSize(1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1296340652127858690L);
        IPage<SampleUser> page = userMapper.selectPage(userPage, queryWrapper);
        System.out.println(page);
    }
}
