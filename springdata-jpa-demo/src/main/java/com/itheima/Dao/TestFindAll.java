package com.itheima.Dao;

import com.itheima.entity.User;
import com.itheima.rep.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFindAll {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        User user = new User("zhangsanlisi", "前端", 1);
         userRepository.save(user);
        String str = "zhangsanlisi";
        List<User> users=userRepository.findByName(str);
        System.out.println(users);

        List<UserPortDao> viewInfos = userRepository.findViewInfo();
        for (UserPortDao viewInfo : viewInfos) {
            System.out.println(viewInfo.getId() + viewInfo.getJob() + viewInfo.getName() + viewInfo.getPid() + viewInfo.getPartname());
        }


    }

}
