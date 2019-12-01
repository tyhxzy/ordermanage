package dao;

import domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    @Select("select * from userinfo where username = #{username}")
    @Results({
            @Result(id = true, property = "id",column = "id"),
            @Result(property = "roles",column = "id",many = @Many(select = "dao.RoleDao.findByUserId"))
    })
    UserInfo findByUsername(String username);

    @Select("select * from userinfo")
    List<UserInfo> findAll();

    @Insert("insert into userinfo values(#{id},#{username},#{email},#{password},#{phoneNum},#{status})")
    void addUser(UserInfo user);

    @Select("select * from userinfo where id = #{id}")
    @Results({
            @Result(id = true, property = "id",column = "id"),
            @Result(property = "roles",column = "id",many = @Many(select = "dao.RoleDao.findByUserId"))
    })
    UserInfo findById(String id);

    @Insert("insert into user_role values (#{userId},#{roleId})")
    void addUserRole(@Param("userId") String userId, @Param("roleId") String roleId);
}
