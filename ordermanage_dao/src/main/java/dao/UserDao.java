package dao;

import domain.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from userinfo where username = #{username}")
    @Results({
            @Result(id = true, property = "id",column = "id"),
            @Result(property = "roles",column = "id",many = @Many(select = "dao.RoleDao.findByUserId"))
    })
    UserInfo findByUsername(String username);
}
