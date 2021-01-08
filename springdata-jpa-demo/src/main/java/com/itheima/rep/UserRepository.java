package com.itheima.rep;

import com.itheima.Dao.UserPortDao;
import com.itheima.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //添加用户
    User save(User user);

    //条件查询
    List<User> findByName(String name);

    //关联查询    @Query可以用于自定义sql语句,如果是修改需加上@Modifying ,这是一条关联查询语句,返回的结果映射到新对象中，新对象为接口，里面含有对应参数的构造方法。
    @Query(value="select u.id,u.name,u.job,u.pid,p.partname  from user u left join part p on u.pid=p.pid ",nativeQuery = true)
    public   List<UserPortDao>   findViewInfo();



}
